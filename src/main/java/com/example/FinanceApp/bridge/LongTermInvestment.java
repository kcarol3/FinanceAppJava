package com.example.FinanceApp.bridge;

public class LongTermInvestment extends Investment {
    public LongTermInvestment(InvestmentType investmentType) {
        super(investmentType);
    }

    @Override
    public String makeInvestment(double amount) {
        return "Long term investment in " + investmentType.toString() + " by the sum of " + amount + "\n";
    }
}
