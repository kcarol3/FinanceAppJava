package com.example.FinanceApp.bridge;

public class ShortTermInvestment extends Investment {
    public ShortTermInvestment(InvestmentType investmentType) {
        super(investmentType);
    }

    @Override
    public String makeInvestment(double amount) {
        return "Short term investment in " + investmentType.toString() + " by the sum of " + amount + "\n";
    }
}
