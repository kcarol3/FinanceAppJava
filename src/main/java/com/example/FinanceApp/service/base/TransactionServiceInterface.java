package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Transaction;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public interface TransactionServiceInterface {
    Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto) throws ParseException;

    Transaction createRecurringTransaction(Long transactionId, String frequency);
}
