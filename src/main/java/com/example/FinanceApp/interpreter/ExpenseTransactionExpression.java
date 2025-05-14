package com.example.FinanceApp.interpreter;

import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.iterator.ExpenseTransactionIterator;

import java.util.List;

//Tydzien 4, Wzorzec Interpreter 2, regula do obliczenia sumy wydatkow
public class ExpenseTransactionExpression implements ExpenseExpression {
    private final Long accountId;

    public ExpenseTransactionExpression(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public double expenseExpressionInterpret(List<Transaction> transactions) {
        ExpenseTransactionIterator iterator = new ExpenseTransactionIterator(transactions, accountId);

        double totalExpenses = 0.0;

        while (iterator.hasNext()) {
            ExpenseTransaction transaction = (ExpenseTransaction) iterator.next();
            totalExpenses += transaction.getAmount();
        }

        return totalExpenses;
    }
}
//Koniec, Tydzien 4, Wzorzec Interpreter 2

