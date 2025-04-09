package com.example.FinanceApp.visitor;

//Tydzien 5, Wzorzec Visitor 2, generowanie wykresow dla roznych danych finansowych
public interface FinancialData {
    void accept(FinancialChartVisitor visitor);
}
//Koniec, Tydzien 5, Wzorzec Visitor 2