package com.example.FinanceApp.entity;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("EXPENSE")
public class ExpenseTransaction extends Transaction {
    public ExpenseTransaction(Double amount, String currency, String name, String description, Account account, LocalDateTime date) {
        super(amount, currency, name, description, account, date);
    }

    public ExpenseTransaction() {

    }

    @Override
    public void processTransaction(Account account) {
        account.withdraw(this.getAmount());
    }
}
