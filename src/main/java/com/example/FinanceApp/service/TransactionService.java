package com.example.FinanceApp.service;

import com.example.FinanceApp.adapter.DateFormatTimeOptionalAdapter;
import com.example.FinanceApp.adapter.ToPlnAdapter;
import com.example.FinanceApp.decorator.TransactionValidationException;
import com.example.FinanceApp.decorator.TransactionValidator;
import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.factory.TransactionFactory;
import com.example.FinanceApp.interpreter.BalanceExpression;
import com.example.FinanceApp.interpreter.ExpenseExpression;
import com.example.FinanceApp.interpreter.ExpenseTransactionExpression;
import com.example.FinanceApp.interpreter.MinimumBalanceExpression;
import com.example.FinanceApp.observer.TransactionAddedEvent;
import com.example.FinanceApp.repository.TransactionRepository;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import com.example.FinanceApp.service.base.TransactionService.RecurringTransactionServiceInterface;
import com.example.FinanceApp.service.base.TransactionService.TransactionAnalysisServiceInterface;
import com.example.FinanceApp.service.base.TransactionService.TransactionCreationServiceInterface;
import com.example.FinanceApp.service.base.TransactionService.TransactionValidationServiceInterface;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import com.example.FinanceApp.strategy.taxStrategy.TaxService;
import org.springframework.context.ApplicationEventPublisher;
import com.example.FinanceApp.state.TransactionContext;
import com.example.FinanceApp.state.TransactionStateType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService implements TransactionCreationServiceInterface, TransactionAnalysisServiceInterface, RecurringTransactionServiceInterface {

    private static final double MIN_BALANCE = 50.0;
    private final TransactionFactory transactionFactory;
    private final TransactionRepository transactionRepository;
    private final ToPlnAdapter toPlnAdapter;
    private final DateFormatTimeOptionalAdapter dateFormatAdapter;
    private final AccountServiceInterface accountService;
    private final ApplicationEventPublisher publisher;
    private final TaxService taxService;
    private final TransactionValidator transactionValidator;

    public TransactionService(TransactionFactory transactionFactory, TransactionRepository transactionRepository, ToPlnAdapter toPlnAdapter, DateFormatTimeOptionalAdapter dateFormatAdapter, AccountServiceInterface accountService, ApplicationEventPublisher publisher, TaxService taxService, TransactionValidator transactionValidator) {
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
        this.toPlnAdapter = toPlnAdapter;
        this.dateFormatAdapter = dateFormatAdapter;
        this.accountService = accountService;
        this.publisher = publisher;
        this.taxService = taxService;
        this.transactionValidator = transactionValidator;
    }

    @Override
    public Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto) {
        Transaction transaction = null;
        try {
            transaction = initializeTransaction(type, transactionDto);
            processTransaction(type, transaction);
            saveTransaction(transaction);
            return transaction;
        } catch (IllegalArgumentException e) {
            throw new TransactionValidationException("Transaction validation failed: " + e.getMessage(), e);
        } catch (Exception e) {
            handleTransactionFailure(transaction);
            throw new TransactionValidationException("Transaction failed: " + e.getMessage(), e);
        }
    }

    @Override
    public double calculateTotalExpenses(Long accountId) {
        List<Transaction> transactions = transactionRepository.findAll();
        ExpenseExpression expenseExpression = new ExpenseTransactionExpression(accountId);

        return expenseExpression.expenseExpressionInterpret(transactions);
    }

    @Override
    public Transaction createRecurringTransaction(Long transactionId, String frequency) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        RecurringTransaction recurringTransaction = new RecurringTransaction(
                transaction.clone(),
                LocalDate.now().plusDays(1),
                frequency
        );

        return transactionRepository.save(recurringTransaction);
    }

    private void extracted(String type, Transaction transaction) {
        if ("EXPENSE".equalsIgnoreCase(type)) {
            BalanceExpression minBalanceRule = new MinimumBalanceExpression(MIN_BALANCE);

            if (!minBalanceRule.balanceExpressionInterpret(transaction)) {
                throw new TransactionValidationException("Expense transaction failed validation: Insufficient balance.");
            }
        }
    }

    private void processTransaction(String type, Transaction transaction) {
        if ("INCOME".equalsIgnoreCase(type)) {
            Double newIncome = transaction.getAmount() - taxService.calculateTax(transaction.getAmount(), transaction.getCurrency());
            transaction.setAmount(newIncome);
        }
        transactionValidator.validate(transaction);
        Account account = transaction.getAccount();
        accountService.createAndSaveAccountMemento(account);
        TransactionContext context = new TransactionContext(transaction);
        context.process();
        transaction.processTransaction(account);
        publisher.publishEvent(new TransactionAddedEvent(this, transaction));
    }

    private void saveTransaction(Transaction transaction) {
        TransactionContext context = new TransactionContext(transaction);
        transactionRepository.save(context.getTransaction());
    }

    private void handleTransactionFailure(Transaction transaction) {
        if (transaction != null) {
            TransactionContext context = new TransactionContext(transaction);
            context.cancel();
            transactionRepository.save(context.getTransaction());
        }
    }

    private Transaction initializeTransaction(String type, TransactionDTO transactionDto) {
        Double convertedAmount = toPlnAdapter.convert(transactionDto.getAmount(), transactionDto.getCurrency());
        LocalDateTime convertedDate = dateFormatAdapter.convertDate(transactionDto.getDateString());
        transactionDto.setAmount(convertedAmount);
        transactionDto.setDate(convertedDate);
        Transaction transaction = transactionFactory.createTransaction(type, transactionDto);
        transaction.setState(TransactionStateType.PLANNED);
        extracted(type, transaction);
        return transaction;
    }
}
