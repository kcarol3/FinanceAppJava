package com.example.FinanceApp.state;

public class PlannedState implements TransactionState{
    public void process(TransactionContext context) {
        System.out.println("Wykonywanie transakcji...");
        context.setState(new CompletedState());
        context.getTransaction().setState(TransactionStateType.COMPLETED);
    }

    public void cancel(TransactionContext context) {
        System.out.println("Anulowanie transakcji...");
        context.setState(new CancelledState());
        context.getTransaction().setState(TransactionStateType.FAILED);
    }

    public String getName() {
        return "PLANNED";
    }
}
