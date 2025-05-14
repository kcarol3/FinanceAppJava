package com.example.FinanceApp.visitor;

public class InvoiceTotalCalculatorImpl implements InvoiceTotalCalculator {
    private double totalAmount = 0.0;

    @Override
    public void visit(InternetService internetService) {
        totalAmount += internetService.getAmount();
    }

    @Override
    public void visit(PhoneService phoneService) {
        totalAmount += phoneService.getAmount();
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

