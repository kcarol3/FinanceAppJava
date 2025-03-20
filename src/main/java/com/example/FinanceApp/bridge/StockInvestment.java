package com.example.FinanceApp.bridge;

import org.springframework.stereotype.Component;

@Component
public class StockInvestment implements InvestmentType {
    @Override
    public String invest(Double amount) {
        return "Investing in stock by the sum of " + amount + " done.";
    }

    @Override
    public String toString() {
        return "stock";
    }
}
