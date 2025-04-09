package com.example.FinanceApp.observer.alert;

import com.example.FinanceApp.entity.base.Account;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Account account, Double amount, String reason);
}
