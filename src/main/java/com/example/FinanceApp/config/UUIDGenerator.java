package com.example.FinanceApp.config;

import java.util.UUID;

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
