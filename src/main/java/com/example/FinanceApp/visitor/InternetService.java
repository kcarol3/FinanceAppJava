package com.example.FinanceApp.visitor;

public class InternetService implements InvoiceItem{
    private double amount;

    public InternetService(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public void accept(InvoiceTotalCalculator visitor) {
        visitor.visit(this);
    }
}
