package com.example.FinanceApp.config;

import java.util.UUID;

//Tydzień 1, Wzorzec Singleton 2, tworzenie UUID transakcji
//Tydzień 6, SRP 2
public class UUIDGenerator {
    private static volatile UUIDGenerator instance;

    private UUIDGenerator(){}

    public static UUIDGenerator getInstance() {
        if (instance == null) {
            synchronized (UUIDGenerator.class) {
                if (instance == null) {
                    instance = new UUIDGenerator();
                }
            }
        }
        return instance;
    }

    public String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

}
//Tydzień 6, SRP 2, Koniec
//Koniec, Tydzień 1, Wzorzec Singleton 2
