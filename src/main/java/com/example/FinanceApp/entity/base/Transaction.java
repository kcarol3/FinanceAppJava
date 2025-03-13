package com.example.FinanceApp.entity.base;

import com.example.FinanceApp.config.UUIDGenerator;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction implements Cloneable {

    @Id
    @GeneratedValue
    private Long id;
    private Double amount;
    private String name;
    private String UUID;
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Date date;

    public Transaction(Double amount, String name, String description, Account account) {
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.account = account;
        this.UUID = UUIDGenerator.getInstance().generateTransactionId();
        this.date = new Date();
    }

    public Transaction() {

    }

    public abstract void processTransaction(Account account);

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUUID() {
        return UUID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Transaction clone() {
        try {
            Transaction clone = (Transaction) super.clone();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
