package com.example.FinanceApp.proxy;

import com.example.FinanceApp.decorator.TransactionValidator;
import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.interpreter.ExpenseExpression;
import com.example.FinanceApp.interpreter.ExpenseTransactionExpression;
import com.example.FinanceApp.repository.TransactionRepository;
import com.example.FinanceApp.service.TransactionService;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//Tydzień 3, Wzorzec Proxy 2, ograniczenie liczby transakcji na godzinę
@Service
public class LimitingTransactionServiceProxy implements TransactionServiceInterface {

    private final TransactionService realTransactionService;
    private final List<LocalDateTime> transactionTimestamps;
    private final TransactionValidator transactionValidator;
    private final TransactionRepository transactionRepository;


    public LimitingTransactionServiceProxy(TransactionService realTransactionService, TransactionValidator transactionValidator, TransactionRepository transactionRepository) {
        this.realTransactionService = realTransactionService;
        this.transactionValidator = transactionValidator;
        this.transactionRepository = transactionRepository;
        this.transactionTimestamps = new ArrayList<>();
    }

    @Override
    public Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto) {
        int transactionLimit = 3;
        LocalDateTime now = LocalDateTime.now();

        transactionTimestamps.removeIf(timestamp -> ChronoUnit.HOURS.between(timestamp, now) > 1);

        if (transactionTimestamps.size() >= transactionLimit) {
            throw new IllegalArgumentException("Limit transakcji w tym okresie został przekroczony.");
        }

        Transaction transaction = realTransactionService.createAndSaveTransaction(type, transactionDto);

        transactionTimestamps.add(now);

        return transaction;
    }
    //Koniec, Tydzień 3, Wzorzec Proxy 2

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

