package com.example.FinanceApp.interpreter;

import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.base.Transaction;

import java.util.List;

//Tydzien 4, Wzorzec Interpreter 2, regula do obliczenia sumy wydatkow
public class ExpenseTransactionExpression implements ExpenseExpression {
    private final Long accountId;

    public ExpenseTransactionExpression(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public double interpret(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction instanceof ExpenseTransaction)
                .filter(transaction -> transaction.getAccount().getId().equals(accountId))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}
//Koniec, Tydzien 4, Wzorzec Interpreter 2

