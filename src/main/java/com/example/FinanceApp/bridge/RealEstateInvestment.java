package com.example.FinanceApp.bridge;

import org.springframework.stereotype.Component;

@Component
public class RealEstateInvestment implements InvestmentType {

    @Override
    public String invest(Double amount) {
        return "Investing in real estate by the sum of " + amount + "done.";
    }

    @Override
    public String toString() {
        return "real estate";
    }
}
