package com.example.FinanceApp.bridge;

// Tydzien 2, Wzorzec Bridge 2 - abstrkacja dla obiektu inwestowania, po ktorym dziedziczą dalej nowe typy inwestowania
public abstract class Investment {
    protected InvestmentType investmentType;

    public Investment(InvestmentType investmentType) {
        this.investmentType = investmentType;
    }

    public abstract String makeInvestment(double amount);
}
// Koniec, Tydzien 2, wzorzec Bridge 2
