package com.example.FinanceApp.decorator;

import com.example.FinanceApp.entity.base.Transaction;

public class BasicTransactionValidator implements TransactionValidator {
    private double amount;

    @Override
    public void validate(Transaction transaction) throws IllegalArgumentException {
        if (transaction.getAmount() == null) {
            //tydzien 8, zwracanie wyjątku 2
            throw new IllegalArgumentException("Amount cannot be null");
        }
    }
}
