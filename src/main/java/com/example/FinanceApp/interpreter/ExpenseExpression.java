package com.example.FinanceApp.interpreter;

import com.example.FinanceApp.entity.base.Transaction;

import java.util.List;

public interface ExpenseExpression {
    double interpret(List<Transaction> transactions);
}
