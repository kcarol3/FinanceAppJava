package com.example.FinanceApp.service.log;

import org.springframework.stereotype.Service;

@Service
public class ConsoleLogger {
    public void logToConsole(String message) {
        System.out.println("[LOG]: " + message);
    }
}
