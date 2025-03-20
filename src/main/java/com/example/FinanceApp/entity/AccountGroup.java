package com.example.FinanceApp.entity;

import com.example.FinanceApp.Composite.base.AccountGroupInterface;
import com.example.FinanceApp.entity.base.Account;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AccountGroup implements AccountGroupInterface {
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "account_group_id") // Powiązanie kont z grupą
    private List<Account> accounts = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Klucz generowany automatycznie
    private Long id;

    public AccountGroup(String name) {
        this.name = name;
    }

    public AccountGroup() {

    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setAccountGroup(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setAccountGroup(null);
    }

    @Override
    public double getFullBalance() {
        return accounts.stream().mapToDouble(Account::getFullBalance).sum();
    }

    @Override
    public String showDetails() {
        StringBuilder message = new StringBuilder();
        for (Account account : accounts) {
            message.append(account.showDetails()).append("/n");
        }

        return message.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

