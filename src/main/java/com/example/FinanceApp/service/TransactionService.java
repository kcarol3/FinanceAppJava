package com.example.FinanceApp.service;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.factory.TransactionFactory;
import com.example.FinanceApp.repository.TransactionRepository;
import com.example.FinanceApp.service.base.TransactionServiceInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService implements TransactionServiceInterface {

    private TransactionFactory transactionFactory;
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionFactory transactionFactory, TransactionRepository transactionRepository) {
        this.transactionFactory = transactionFactory;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto) {
        Transaction transaction = transactionFactory.createAccount(type, transactionDto);
        transaction.processTransaction(transaction.getAccount());
        return transactionRepository.save(transaction);
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
