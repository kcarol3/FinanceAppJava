package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.base.Transaction;
import org.springframework.stereotype.Service;

import java.text.ParseException;

//Tydzień 7, ISP 5 ORYGINAŁ
@Service
public interface TransactionServiceInterface {
    Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto) throws ParseException;
    void validateTransaction(Transaction transaction) throws IllegalArgumentException;
    double calculateTotalExpenses(Long accountId);
    Transaction createRecurringTransaction(Long transactionId, String frequency);
}
