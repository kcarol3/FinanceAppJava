package com.example.FinanceApp.state;

public class CancelledState implements TransactionState{
    public void process(TransactionContext context) {
        System.out.println("Nie można wykonać anulowanej transakcji.");
    }

    public void cancel(TransactionContext context) {
        System.out.println("Transakcja już anulowana.");
    }

    public String getName() {
        return "FAILED";
    }
}
