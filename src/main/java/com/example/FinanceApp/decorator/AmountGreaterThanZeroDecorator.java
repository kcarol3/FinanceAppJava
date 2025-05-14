package com.example.FinanceApp.decorator;

import com.example.FinanceApp.entity.base.Transaction;


// Tydzien 2, Wzorzec Decorator 1 - implementacja walidatora, ktory sprawdza czy transakcje mają kwote wieksza od zera
public class AmountGreaterThanZeroDecorator implements TransactionValidator {

    private final TransactionValidator wrappedValidator;

    public AmountGreaterThanZeroDecorator(TransactionValidator wrappedValidator) {
        this.wrappedValidator = wrappedValidator;
    }

    @Override
    public void validate(Transaction transaction) throws IllegalArgumentException {
        wrappedValidator.validate(transaction);
        if (transaction.getAmount() <= 0) {
            //tydzien 8, zwracanie wyjątku 1
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }
}
// Koniec, Tydzien 2, wzorzec Decorator 1


