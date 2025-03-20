package com.example.FinanceApp.config;

public class AccountConfig {
    private static final AccountConfig instance = new AccountConfig();

    private AccountConfig() {}

    public static AccountConfig getInstance() {
        return instance;
    }

    public Double getBalance() {
        return 100.0;
    }

    public Double getInterestRate() {
        return 4.5;
    }
}
