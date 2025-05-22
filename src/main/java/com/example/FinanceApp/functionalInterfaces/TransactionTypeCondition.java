package com.example.FinanceApp.functionalInterfaces;

@FunctionalInterface
public interface TransactionTypeCondition {
    boolean isType(String type);
}

