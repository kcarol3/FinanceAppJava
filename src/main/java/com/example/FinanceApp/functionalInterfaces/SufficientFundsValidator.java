package com.example.FinanceApp.functionalInterfaces;

import com.example.FinanceApp.entity.base.Account;

@FunctionalInterface
public interface SufficientFundsValidator {
    void validate(Account account, Double amount);
}
