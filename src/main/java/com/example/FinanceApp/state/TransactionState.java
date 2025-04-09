package com.example.FinanceApp.state;

//Tydzien 5, State 2, dodanie stanu transakcji
public interface TransactionState {
    void process(TransactionContext context);
    void cancel(TransactionContext context);
    String getName();
}
//Koniec, Tydzien 5, Wzorzec State 2