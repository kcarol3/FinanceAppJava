package com.example.FinanceApp.observer.alert;

import com.example.FinanceApp.entity.base.Account;

public interface Observer {
    void onBalanceChanged(Account account, Double changeAmount, String reason);
}
