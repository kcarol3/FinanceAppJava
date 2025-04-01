package com.example.FinanceApp.flyweight;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BankFactory {
    private final Map<String, Bank> banks = new ConcurrentHashMap<>();

    public BankInterface getBank(String name, String bicCode) {
        return banks.computeIfAbsent(name, k -> new Bank(name, bicCode));
    }
}

