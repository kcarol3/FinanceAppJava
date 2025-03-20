package com.example.FinanceApp.bridge;

public abstract class Investment {
    protected InvestmentType investmentType;

    public Investment(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }

    public abstract String makeInvestment(double amount);
}
