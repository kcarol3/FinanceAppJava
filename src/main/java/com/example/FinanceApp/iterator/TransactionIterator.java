package com.example.FinanceApp.iterator;

import com.example.FinanceApp.entity.base.Transaction;

import java.util.Iterator;
import java.util.List;

//Tydzien 4, Wzorzec Iterator 2, iterator do przechodzenia po transakcjach
public class TransactionIterator implements Iterator<Transaction> {
    private final List<Transaction> transactions;
    private final Long accountId;
    private int currentIndex = 0;

    public TransactionIterator(List<Transaction> transactions, Long accountId) {
        this.transactions = transactions;
        this.accountId = accountId;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < transactions.size()) {
            Transaction transaction = transactions.get(currentIndex);
            if (transaction.getAccount().getId().equals(accountId)) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public Transaction next() {
        if (!hasNext()) {
            throw new java.util.NoSuchElementException("No more transactions available.");
        }
        return transactions.get(currentIndex++);
    }
}
//Koniec, Tydzien 4, Wzorzec Iterator 2
