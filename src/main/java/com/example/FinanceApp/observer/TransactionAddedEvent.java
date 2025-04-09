package com.example.FinanceApp.observer;

import com.example.FinanceApp.entity.base.Transaction;
import org.springframework.context.ApplicationEvent;

// Tydzien 5, Wzorzec Observer 1, uruchamiany przy dodawaniu transakcji sprawdzenie czy nie jest to podejrzana transakcja
public class TransactionAddedEvent extends ApplicationEvent {
    private final Transaction transaction;

    public TransactionAddedEvent(Object source, Transaction transaction) {
        super(source);
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
// Tydzien 5, Wzorzec Observer 1, koniec