package com.example.FinanceApp.interpreter;

import com.example.FinanceApp.entity.base.Transaction;

public interface BalanceExpression {
    boolean interpret(Transaction transaction);
}
