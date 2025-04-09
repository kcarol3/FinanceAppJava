package com.example.FinanceApp.visitor;

public class InvoiceTotalCalculatorImpl implements InvoiceTotalCalculator {
    private double total = 0.0;

    @Override
    public void visit(InternetService internetService) {
        total += internetService.getAmount();
    }

    @Override
    public void visit(PhoneService phoneService) {
        total += phoneService.getAmount();
    }

    public double getTotal() {
        return total;
    }
}

