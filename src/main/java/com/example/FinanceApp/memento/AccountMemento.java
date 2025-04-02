package com.example.FinanceApp.memento;

import com.example.FinanceApp.entity.base.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;

//Tydzien 4, Wzorzec Memento 1, tworzenie kopii salda konta
@Entity
public class AccountMemento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double balance;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, unique = false)
    private Account account;

    public AccountMemento(Account account, double balance) {
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
        this.account = account;
    }

    public AccountMemento() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public double getBalance() { return balance; }

    public Account getAccount() { return account; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }

    public void setBalance(double balance) { this.balance = balance; }

    public void setAccount(Account account) { this.account = account; }
}
//Koniec, Tydzien 4, Wzorzec Memento 1

