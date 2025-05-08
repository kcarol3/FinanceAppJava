package com.example.FinanceApp.entity.base;

import com.example.FinanceApp.config.UUIDGenerator;
import com.example.FinanceApp.state.TransactionStateType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type", discriminatorType = DiscriminatorType.STRING)
//tydzien 7, dependency inversion 2
public abstract class Transaction implements Cloneable {

    @Id
    @GeneratedValue
    private Long id;
    private Double amount;
    private String currency;
    private String name;
    private String UUID;
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    private TransactionStateType state;

    public Transaction(Double amount, String currency, String name, String description, Account account, LocalDateTime date, TransactionStateType state) {
        this.amount = amount;
        this.currency = currency;
        this.name = name;
        this.description = description;
        this.account = account;
        this.UUID = UUIDGenerator.getInstance().generateTransactionId();
        this.date = date;
        this.state = TransactionStateType.PLANNED;
    }

    public Transaction(Double amount, String currency, String name, String description, Account account, LocalDateTime date) {
        this.amount = amount;
        this.currency = currency;
        this.name = name;
        this.description = description;
        this.account = account;
        this.UUID = UUIDGenerator.getInstance().generateTransactionId();
        this.date = date;
    }

    public Transaction() {

    }

    public abstract void processTransaction(Account account);

    @Override
    public String toString() {
        return name + " | " + description + " | " + amount + " PLN";
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) { this.currency = currency; }

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

    public TransactionStateType getState() { return state; }

    public void setState(TransactionStateType state) { this.state = state; }

    //Tydzień 1, Wzorzec Prototype 2, klonowanie transakcji na potrzeby transakcji cyklicznej
    @Override
    public Transaction clone() {
        try {
            Transaction clone = (Transaction) super.clone();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    //Koniec, Tydzień 1, Wzorzec Prototype 2
}
