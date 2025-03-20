package com.example.FinanceApp.decorator;

import com.example.FinanceApp.entity.base.Transaction;

public interface TransactionValidator {
    void validate(Transaction transaction) throws IllegalArgumentException;
}
