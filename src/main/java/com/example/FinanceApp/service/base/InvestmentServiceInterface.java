package com.example.FinanceApp.service.base;

import org.springframework.stereotype.Service;

@Service
public interface InvestmentServiceInterface {
    public String invest(String type, String term, double amount);
}
