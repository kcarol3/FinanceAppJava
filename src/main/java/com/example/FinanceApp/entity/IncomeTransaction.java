package com.example.FinanceApp.entity;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("INCOME")
public class IncomeTransaction extends Transaction {
    public IncomeTransaction(Double amount, String currency, String name, String description, Account account, LocalDateTime date) {
        super(amount, currency, name, description, account,date);
    }

    public IncomeTransaction() {

    }

    @Override
    public void processTransaction(Account account) {
        account.deposit(this.getAmount());
    }
}
