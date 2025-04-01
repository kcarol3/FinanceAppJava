package com.example.FinanceApp.iterator;

import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.base.Transaction;

import java.util.Iterator;
import java.util.List;

//Tydzien 4, Wzorzec Iterator 1, iterator do przechodzenia po transakcjach typu wydatek
public class ExpenseTransactionIterator implements Iterator<Transaction> {
    private final List<Transaction> transactions;
    private final Long accountId;
    private int currentIndex = 0;

    public ExpenseTransactionIterator(List<Transaction> transactions, Long accountId) {
        this.transactions = transactions;
        this.accountId = accountId;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < transactions.size()) {
            Transaction transaction = transactions.get(currentIndex);
            if (transaction instanceof ExpenseTransaction && transaction.getAccount().getId().equals(accountId)) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public Transaction next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }
        return transactions.get(currentIndex++);
    }
}
//Koniec, Tydzien 4, Wzorzec Iterator 1
