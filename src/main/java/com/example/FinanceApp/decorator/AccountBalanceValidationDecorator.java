package com.example.FinanceApp.decorator;

import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.base.Transaction;

import java.util.function.Predicate;

// Tydzien 2, Wzorzec Decorator 1 - implementacja walidatora, ktory sprawdza czy z konta mozna pobrac konkretna kwote
public class AccountBalanceValidationDecorator implements TransactionValidator {
    private final TransactionValidator wrappedValidator;

    //Tydzień 9, interfejs Predicate 2
    private final Predicate<Transaction> isInsufficientFunds;

    public AccountBalanceValidationDecorator(TransactionValidator wrappedValidator) {
        this.wrappedValidator = wrappedValidator;


        this.isInsufficientFunds = transaction ->
                transaction instanceof ExpenseTransaction &&
                        transaction.getAccount().getBalance() < transaction.getAmount();
    }

    @Override
    public void validate(Transaction transaction) throws IllegalArgumentException {
        wrappedValidator.validate(transaction);

        if (isInsufficientFunds.test(transaction)) {
            // Tydzień 8, wyjątek
            throw new IllegalArgumentException("Insufficient funds for this transaction");
        }
    }

    //Koniec, Tydzień 9, interfejs Predicate 2

//import com.example.FinanceApp.entity.base.Account;
//    private final TransactionValidator wrappedValidator;
//
//    public AccountBalanceValidationDecorator(TransactionValidator wrappedValidator) {
//        this.wrappedValidator = wrappedValidator;
//    }
//
//    @Override
//    public void validate(Transaction transaction) throws IllegalArgumentException {
//        wrappedValidator.validate(transaction);
//
//        Account account = transaction.getAccount();
//        Double balance = account.getBalance();
//        if (transaction instanceof ExpenseTransaction && balance < transaction.getAmount()) {
//            //tydzien 8, zwracanie wyjątku 3
//            throw new IllegalArgumentException("Insufficient funds for this transaction");
//        }
//    }
}
// Koniec, Tydzien 2, wzorzec Decorator 2

