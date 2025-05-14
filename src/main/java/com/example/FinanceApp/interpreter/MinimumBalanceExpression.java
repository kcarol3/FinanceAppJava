package com.example.FinanceApp.interpreter;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;

//Tydzien 4, Wzorzec Interpreter 1, regula do sprawdzania, czy minimalne saldo wynosi 50
public class MinimumBalanceExpression implements BalanceExpression {
    private final double minBalance;

    public MinimumBalanceExpression(double minBalance) {
        this.minBalance = minBalance;
    }

    @Override
    public boolean balanceExpressionInterpret(Transaction transaction) {
        Account account = transaction.getAccount();
        return (account.getBalance() - transaction.getAmount()) >= minBalance;
    }

}
//Koniec, Tydzien 4, Wzorzec Interpreter 1

