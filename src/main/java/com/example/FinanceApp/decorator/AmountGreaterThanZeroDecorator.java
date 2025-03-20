package com.example.FinanceApp.decorator;

import com.example.FinanceApp.entity.base.Transaction;

public class AmountGreaterThanZeroDecorator implements TransactionValidator {

    private final TransactionValidator wrappedValidator;

    public AmountGreaterThanZeroDecorator(TransactionValidator wrappedValidator) {
        this.wrappedValidator = wrappedValidator;
    }

    @Override
    public void validate(Transaction transaction) throws IllegalArgumentException {
        wrappedValidator.validate(transaction);
        if (transaction.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}


