package com.example.FinanceApp.visitor;

public interface InvoiceTotalCalculator {
    void visit(InternetService internetService);
    void visit(PhoneService phoneService);
}


