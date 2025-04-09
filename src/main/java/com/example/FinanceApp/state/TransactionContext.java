package com.example.FinanceApp.state;

import com.example.FinanceApp.entity.base.Transaction;

public class TransactionContext {
    private Transaction transaction;
    private TransactionState state;

    public TransactionContext(Transaction transaction) {
        this.transaction = transaction;
        this.state = resolveState(transaction.getState());
    }

    public void process() {
        state.process(this);
    }

    public void cancel() {
        state.cancel(this);
    }

    private TransactionState resolveState(TransactionStateType stateType) {
        return switch (stateType) {
            case PLANNED -> new PlannedState();
            case COMPLETED -> new CompletedState();
            case FAILED -> new CancelledState();
        };
    }

    public void setState(TransactionState state) {
        this.state = state;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
