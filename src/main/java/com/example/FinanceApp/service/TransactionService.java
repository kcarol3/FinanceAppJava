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
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.context.ApplicationEventPublisher;
import com.example.FinanceApp.state.TransactionContext;
import com.example.FinanceApp.state.TransactionStateType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService implements TransactionServiceInterface {

    private final TransactionFactory transactionFactory;
    private final TransactionRepository transactionRepository;
    private final ToPlnAdapter toPlnAdapter;
    private final DateFormatTimeOptionalAdapter dateFormatAdapter;
    private final TransactionValidator transactionValidator;
    private final AccountServiceInterface accountService;

    private final ApplicationEventPublisher publisher;

    public TransactionService(TransactionFactory transactionFactory, TransactionRepository transactionRepository, ToPlnAdapter toPlnAdapter, DateFormatTimeOptionalAdapter dateFormatAdapter, TransactionValidator transactionValidator, AccountServiceInterface accountService, ApplicationEventPublisher publisher) {
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
        this.toPlnAdapter = toPlnAdapter;
        this.dateFormatAdapter = dateFormatAdapter;
        this.transactionValidator = transactionValidator;
        this.accountService = accountService;
        this.publisher = publisher;
    }

    @Override
    public Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto) {
        Transaction transaction = null;

        try {
            Double convertedAmount = toPlnAdapter.convert(transactionDto.getAmount(), transactionDto.getCurrency());
            LocalDateTime convertedDate = dateFormatAdapter.convertDate(transactionDto.getDateString());

            transactionDto.setAmount(convertedAmount);
            transactionDto.setDate(convertedDate);

            transaction = transactionFactory.createAccount(type, transactionDto);
            transaction.setState(TransactionStateType.PLANNED);

            if ("EXPENSE".equalsIgnoreCase(type)) {
                BalanceExpression minBalanceRule = new MinimumBalanceExpression(50.0);

                if (!minBalanceRule.interpret(transaction)) {
                    throw new TransactionValidationException("Expense transaction failed validation: Insufficient balance.");
                }
            }

            transactionValidator.validate(transaction);

            Account account = transaction.getAccount();
            accountService.createAndSaveAccountMemento(account);

            TransactionContext context = new TransactionContext(transaction);
            context.process();
            publisher.publishEvent(new TransactionAddedEvent(this, transaction));

            return transactionRepository.save(context.getTransaction());
        } catch (IllegalArgumentException e) {
            throw new TransactionValidationException("Transaction validation failed: " + e.getMessage(), e);
        } catch (Exception e) {
            if (transaction != null) {
                TransactionContext context = new TransactionContext(transaction);
                context.cancel();
                transactionRepository.save(context.getTransaction());
            }

            throw new TransactionValidationException("Transaction failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void validateTransaction(Transaction transaction) throws IllegalArgumentException {
        transactionValidator.validate(transaction);
    }

    @Override
    public double calculateTotalExpenses(Long accountId) {
        List<Transaction> transactions = transactionRepository.findAll();
        ExpenseExpression expenseExpression = new ExpenseTransactionExpression(accountId);

        return expenseExpression.interpret(transactions);
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
}
