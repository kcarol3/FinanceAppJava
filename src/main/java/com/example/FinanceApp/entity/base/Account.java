package com.example.FinanceApp.entity.base;

import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.SavingsAccount;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "SAVINGS"),
        @JsonSubTypes.Type(value = OwnAccount.class, name = "OWN")
})
public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;
    private String currency = "PLN";

    @JsonBackReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public void deposit(Double amount) { this.balance += amount; }
    public void withdraw(Double amount) { this.balance -= amount; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    protected Account() {}
    protected Account(Builder<?> builder) {
        this.balance = builder.balance;
    }

    public abstract static class Builder<T extends Builder<T>> {
        private double balance;

        public T balance(double balance) {
            this.balance = balance;
            return self();
        }

        protected abstract T self();

        public abstract Account build();
    }
}
