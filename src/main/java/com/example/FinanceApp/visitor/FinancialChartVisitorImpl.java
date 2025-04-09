package com.example.FinanceApp.visitor;

public class FinancialChartVisitorImpl implements FinancialChartVisitor {
    @Override
    public void visit(Revenue revenue) {
        System.out.println("Generowanie wykresu dla przychodu: " + revenue.getAmount());
    }

    @Override
    public void visit(Expense expense) {
        System.out.println("Generowanie wykresu dla wydatków: " + expense.getAmount());
    }
}
