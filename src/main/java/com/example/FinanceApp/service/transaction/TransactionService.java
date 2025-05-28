package com.example.FinanceApp.service.transaction;

import com.example.FinanceApp.adapter.DateFormatTimeOptionalAdapter;
import com.example.FinanceApp.adapter.ToPlnAdapter;
import com.example.FinanceApp.decorator.TransactionValidationException;
import com.example.FinanceApp.decorator.TransactionValidator;
import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.factory.TransactionFactory;
import com.example.FinanceApp.functionalInterfaces.NextDateCalculator;
import com.example.FinanceApp.functionalInterfaces.TransactionCloner;
import com.example.FinanceApp.functionalInterfaces.TransactionTypeCondition;
import com.example.FinanceApp.functionalInterfaces.TransactionValidationRule;
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
import com.example.FinanceApp.strategy.taxStrategy.TaxService;
import org.springframework.context.ApplicationEventPublisher;
import com.example.FinanceApp.state.TransactionContext;
import com.example.FinanceApp.state.TransactionStateType;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService implements TransactionCreationServiceInterface, TransactionAnalysisServiceInterface, RecurringTransactionServiceInterface {

    private static final double MIN_BALANCE = 50.0;
    private static final int DAYS_TO_ADD = 1;
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
        Transaction transaction;
        transaction = initializeTransaction(type, transactionDto);
        processTransaction(type, transaction);
        saveTransaction(transaction);
        return transaction;
    }

    @Override
    public double calculateTotalExpenses(Long accountId) {
        List<Transaction> transactions = transactionRepository.findAll();
        ExpenseExpression expenseExpression = new ExpenseTransactionExpression(accountId);

        return expenseExpression.expenseExpressionInterpret(transactions);
    }

    // tydzien 9, interfejsy funkcyjne 3 i 4
    @Override
    public Transaction createRecurringTransaction(Long transactionId, String frequency) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction with ID " + transactionId + " not found"));

        TransactionCloner cloner = Transaction::clone;

        Map<String, NextDateCalculator> frequencyCalculators = Map.of(
                "DAILY", current -> current.plusDays(1),
                "WEEKLY", current -> current.plusWeeks(1),
                "MONTHLY", current -> current.plusMonths(1),
                "YEARLY", current -> current.plusYears(1)
        );

        NextDateCalculator calculator = frequencyCalculators.getOrDefault(
                frequency.toUpperCase(),
                current -> current.plusDays(3)
        );

        RecurringTransaction recurringTransaction = new RecurringTransaction(
                cloner.clone(transaction),
                calculator.calculateNextDate(LocalDate.now()),
                frequency
        );

        return transactionRepository.save(recurringTransaction);
    }
    // tydzien 9, interfejsy funkcyjne 3 i 4, koniec

    //tydzien 9, interfejsy funkcyjne, 1 i 2
    private void extracted(String type, Transaction transaction) {
        TransactionValidationRule minBalanceRule = t ->
                t.getAccount().getBalance() - t.getAmount() >= MIN_BALANCE;

        TransactionTypeCondition isExpense = transactionType -> "EXPENSE".equalsIgnoreCase(transactionType);


        if (isExpense.isType(type)) {
            if (!minBalanceRule.validate(transaction)) {
                throw new TransactionValidationException("Expense transaction failed validation: Insufficient balance.");
            }
        }
    }
    //tydzien 9, interfejsy funkcyjne, 1 i 2, koniec

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

    public void handleTransactionFailure(Transaction transaction) {
        if (transaction != null) {
            TransactionContext context = new TransactionContext(transaction);
            context.cancel();
            transactionRepository.save(context.getTransaction());
        }
    }

    private Transaction initializeTransaction(String type, TransactionDTO transactionDto) throws IllegalArgumentException{
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
