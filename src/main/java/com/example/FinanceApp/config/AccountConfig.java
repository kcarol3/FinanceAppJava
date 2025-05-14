package com.example.FinanceApp.config;


//Tydzień 1, Wzorzec Singleton 1, tworzenie konfiguracji kont
//Tydzień 6, SRP 1
public class AccountConfig {
    private static final AccountConfig instance = new AccountConfig();
    private final Double BALANCE_AMOUNT = 500.00;
    private final Double INTEREST_RATE = 4.5;

    private AccountConfig() {
    }

    public static AccountConfig getInstance() {
        return instance;
    }

    public Double getBalance() {
        return BALANCE_AMOUNT;
    }

    public Double getInterestRate() {
        return INTEREST_RATE;
    }
}
//Tydzień 6, SRP 1, koniec
//Koniec, Tydzień 1, Wzorzec Singleton 1
