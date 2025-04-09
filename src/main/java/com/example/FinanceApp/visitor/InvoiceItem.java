package com.example.FinanceApp.visitor;

//Tydzien 5, Wzorzec Visitor 1, obliczanie roznego rodzaju faktur
public interface InvoiceItem {
    void accept(InvoiceTotalCalculator visitor);
}
//Koniec, Tydzien 5, Wzorzec Visitor 1
