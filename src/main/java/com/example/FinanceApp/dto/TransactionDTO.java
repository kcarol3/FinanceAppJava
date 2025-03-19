package com.example.FinanceApp.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class TransactionDTO {
    private Long id;
    private Double amount;
    private String currency;
    private String name;
    private String UUID;
    private String description;
    private Long accountId;
    private String dateString;
    private LocalDateTime date;
    private String type;

    public TransactionDTO(Double amount, String currency, String name, String UUID, String description, Long accountId, String date, String type) {
        this.amount = amount;
        this.currency = currency;
        this.name = name;
        this.UUID = UUID;
        this.description = description;
        this.accountId = accountId;
        this.dateString = date;
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime dateTime) {
        this.date = dateTime;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) { this.currency = currency; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAccount() {
        return accountId;
    }

    public void setAccount(Long accountId) {
        this.accountId = accountId;
    }
}
