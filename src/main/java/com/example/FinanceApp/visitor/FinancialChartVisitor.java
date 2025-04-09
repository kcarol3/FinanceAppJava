package com.example.FinanceApp.visitor;

public interface FinancialChartVisitor {
    void visit(Revenue revenue);
    void visit(Expense expense);
}
