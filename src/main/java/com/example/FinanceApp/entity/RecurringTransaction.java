package com.example.FinanceApp.entity;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Date;

@Entity
@DiscriminatorValue("RECURRING")
public class RecurringTransaction extends Transaction {
    private LocalDate nextExecutionDate;
    private String frequency; // DAILY, WEEKLY, MONTHLY
    public RecurringTransaction(Double amount, String name, String description, Account account) {
        super(amount, name, description, account);
    }

    public RecurringTransaction(Transaction clone, LocalDate nextExecutionDate, String frequency) {
        super(clone.getAmount(), clone.getName(), clone.getDescription(), clone.getAccount());
        this.nextExecutionDate = nextExecutionDate;
        this.frequency = frequency;
    }


    public void updateNextExecutionDate() {
        switch (frequency) {
            case "DAILY":
                nextExecutionDate = nextExecutionDate.plusDays(1);
                break;
            case "WEEKLY":
                nextExecutionDate = nextExecutionDate.plusWeeks(1);
                break;
            case "MONTHLY":
                nextExecutionDate = nextExecutionDate.plusMonths(1);
                break;
        }
    }


    public RecurringTransaction() {
        super();
    }

    @Override
    public void processTransaction(Account account) {
        account.withdraw(this.getAmount());
    }
}
