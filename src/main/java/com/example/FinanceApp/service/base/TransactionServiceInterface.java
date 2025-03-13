package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionServiceInterface {
    Transaction createAndSaveTransaction(String type, TransactionDTO transactionDto);

    Transaction createRecurringTransaction(Long transactionId, String frequency);
}
