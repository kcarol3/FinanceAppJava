package com.example.FinanceApp.state;

public class CompletedState implements TransactionState{
    public void process(TransactionContext context) {
        System.out.println("Transakcja już zakończona.");
    }

    public void cancel(TransactionContext context) {
        System.out.println("Nie można anulować wykonanej transakcji.");
    }

    public String getName() {
        return "COMPLETED";
    }
}
