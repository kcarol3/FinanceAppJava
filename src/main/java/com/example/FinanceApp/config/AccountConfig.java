package com.example.FinanceApp.config;


//Tydzień 1, Wzorzec Singleton 1, tworzenie konfiguracji kont
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
//Koniec, Tydzień 1, Wzorzec Singleton 1
