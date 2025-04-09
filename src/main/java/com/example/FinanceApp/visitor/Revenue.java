package com.example.FinanceApp.visitor;

public class Revenue implements FinancialData {
    private double amount;

    public Revenue(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public void accept(FinancialChartVisitor visitor) {
        visitor.visit(this);
    }
}