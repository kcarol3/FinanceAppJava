package com.example.FinanceApp.entity;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Alert() {
    }

    public Alert(String message, LocalDateTime createdAt, Account account) {
        this.message = message;
        this.createdAt = createdAt;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
