package com.example.FinanceApp.visitor;

public class PhoneService implements InvoiceItem{
    private double amount;

    public PhoneService(double amount) {
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
