package com.example.FinanceApp.functionalInterfaces;

import com.example.FinanceApp.entity.base.Account;

@FunctionalInterface
public interface MoneyTransferOperation {
    void execute(Account from, Account to, Double amount);
}
