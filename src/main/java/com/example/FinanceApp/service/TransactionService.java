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
import com.example.FinanceApp.repository.TransactionRepository;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionService implements TransactionServiceInterface {

    private final TransactionFactory transactionFactory;
    private final TransactionRepository transactionRepository;
    private final ToPlnAdapter toPlnAdapter;
    private final DateFormatTimeOptionalAdapter dateFormatAdapter;
    private final TransactionValidator transactionValidator;

    public TransactionService(TransactionFactory transactionFactory, TransactionRepository transactionRepository, ToPlnAdapter toPlnAdapter, DateFormatTimeOptionalAdapter dateFormatAdapter, TransactionValidator transactionValidator) {
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
        this.toPlnAdapter = toPlnAdapter;
        this.dateFormatAdapter = dateFormatAdapter;
        this.transactionValidator = transactionValidator;
    }

    @Override
    public Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto) {
        try {
            Double convertedAmount = toPlnAdapter.convert(transactionDto.getAmount(), transactionDto.getCurrency());
            LocalDateTime convertedDate = dateFormatAdapter.convertDate(transactionDto.getDateString());

            transactionDto.setAmount(convertedAmount);
            transactionDto.setDate(convertedDate);

            Transaction transaction = transactionFactory.createAccount(type, transactionDto);

            transactionValidator.validate(transaction);

            Account account = transaction.getAccount();
            transaction.processTransaction(account);


            return transactionRepository.save(transaction);
        } catch (IllegalArgumentException e) {
            throw new TransactionValidationException("Transaction validation failed: " + e.getMessage(), e);
        }
    }

    @Override
    public void validateTransaction(Transaction transaction) throws IllegalArgumentException {
        transactionValidator.validate(transaction);
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
