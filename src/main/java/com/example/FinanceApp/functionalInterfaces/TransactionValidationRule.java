package com.example.FinanceApp.functionalInterfaces;

import com.example.FinanceApp.entity.base.Transaction;

@FunctionalInterface
public interface TransactionValidationRule {
    boolean validate(Transaction transaction);
}

