package com.example.FinanceApp.entity.base;

import com.example.FinanceApp.Composite.base.AccountGroupInterface;
import com.example.FinanceApp.entity.AccountGroup;
import com.example.FinanceApp.entity.Alert;
import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.SavingsAccount;
import com.example.FinanceApp.memento.AccountMemento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "SAVINGS"),
        @JsonSubTypes.Type(value = OwnAccount.class, name = "OWN")
})
//tydzien 7, dependency inversion 1
public abstract class Account implements AccountGroupInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected Double balance;
    private String currency = "PLN";

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alert> alerts;

    @JsonBackReference
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_group_id")
    private AccountGroup accountGroup;

    @OneToMany(mappedBy = "account")
    private List<AccountMemento> mementos = new ArrayList<>();

    public void deposit(Double amount) {
        this.balance += amount;
    }

    public void withdraw(Double amount) {
        this.balance -= amount;
    }

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

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AccountGroup getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(AccountGroup accountGroup) {
        this.accountGroup = accountGroup;
    }

    public List<AccountMemento> getMementos() {
        return mementos;
    }

    public void addMementos(AccountMemento memento) {
        if (mementos == null) {
            mementos = new ArrayList<>();
        }
        mementos.add(memento);
    }

    public void restoreFromMemento(AccountMemento memento) {
        this.balance = memento.getBalance();
    }

    protected Account() {
    }

    //Tydzień 1, Wzorzec Builder 2, baza do tworzenia kont użytkownika
    protected Account(Builder<?> builder) {
        this.balance = builder.balance;
        this.mementos = new ArrayList<>();
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
    //Koniec, Tydzień 1, Wzorzec Builder 2
}
