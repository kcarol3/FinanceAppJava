package com.example.FinanceApp.visitor;

public class Expense implements FinancialData {
    private double amount;

    public Expense(double amount) {
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

