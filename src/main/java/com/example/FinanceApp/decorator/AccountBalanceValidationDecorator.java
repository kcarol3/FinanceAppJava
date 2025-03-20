package com.example.FinanceApp.decorator;

import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
// Tydzien 2, Wzorzec Decorator 1 - implementacja walidatora, ktory spradza czy z konta mozna pobrac konkretna kwote
public class AccountBalanceValidationDecorator implements TransactionValidator {

    private final TransactionValidator wrappedValidator;

    public AccountBalanceValidationDecorator(TransactionValidator wrappedValidator) {
        this.wrappedValidator = wrappedValidator;
    }

    @Override
    public void validate(Transaction transaction) throws IllegalArgumentException {
        wrappedValidator.validate(transaction);

        Account account = transaction.getAccount();
        Double balance = account.getBalance();
        if (transaction instanceof ExpenseTransaction && balance < transaction.getAmount()) {
            throw new IllegalArgumentException("Insufficient funds for this transaction");
        }
    }
}
// Koniec, Tydzien 2, wzorzec Decorator 2

