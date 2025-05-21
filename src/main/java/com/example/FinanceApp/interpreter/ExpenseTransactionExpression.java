package com.example.FinanceApp.interpreter;

import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.iterator.ExpenseTransactionIterator;

import java.util.List;
import java.util.function.Predicate;

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

        //Tydzień 9, interfejs Predicate 1
        Predicate<ExpenseTransaction> isLargeExpense = t -> t.getAmount() > 100.0;

        while (iterator.hasNext()) {
            ExpenseTransaction transaction = (ExpenseTransaction) iterator.next();
            if (isLargeExpense.test(transaction)) {
                totalExpenses += transaction.getAmount();
            }
        }
        //Koniec, Tydzień 9, interfejs Predicate 1

//        while (iterator.hasNext()) {
//            ExpenseTransaction transaction = (ExpenseTransaction) iterator.next();
//            totalExpenses += transaction.getAmount();
//        }

        return totalExpenses;
    }
}
//Koniec, Tydzien 4, Wzorzec Interpreter 2

