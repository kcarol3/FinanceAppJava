package com.example.FinanceApp.service.base.TransactionService;

import com.example.FinanceApp.entity.base.Transaction;

//Tydzień 7, ISP 5
public interface TransactionValidationServiceInterface {
    void validateTransaction(Transaction transaction) throws IllegalArgumentException;
}
//Tydzień 7, ISP 5, koniec
