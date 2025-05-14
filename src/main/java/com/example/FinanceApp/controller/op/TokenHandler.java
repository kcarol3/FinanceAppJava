package com.example.FinanceApp.controller.op;

public interface TokenHandler {
    String generate(String userIdOrTransactionId);

    boolean verify(String key, String token);
}
