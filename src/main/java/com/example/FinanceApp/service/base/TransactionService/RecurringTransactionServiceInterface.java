package com.example.FinanceApp.service.base.TransactionService;

import com.example.FinanceApp.entity.base.Transaction;

//Tydzień 7, ISP 5
public interface RecurringTransactionServiceInterface {
    Transaction createRecurringTransaction(Long transactionId, String frequency);
}
//Tydzień 7, ISP 5, koniec