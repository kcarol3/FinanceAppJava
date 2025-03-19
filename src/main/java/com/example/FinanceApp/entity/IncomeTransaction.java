package com.example.FinanceApp.entity;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("INCOME")
public class IncomeTransaction extends Transaction {
    public IncomeTransaction(Double amount, String currency, String name, String description, Account account) {
        super(amount, currency, name, description, account);
    }

    public IncomeTransaction() {

    }

    @Override
    public void processTransaction(Account account) {
        account.deposit(this.getAmount());
    }
}
