package com.example.FinanceApp.service.transaction;

import com.example.FinanceApp.decorator.TransactionValidator;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.service.base.TransactionService.TransactionValidationServiceInterface;
import org.springframework.stereotype.Service;


//tydzien 7, IS 1, uzycie
@Service
public class TransactionValidatorService implements TransactionValidationServiceInterface {
    private final TransactionValidator transactionValidator;

    public TransactionValidatorService(TransactionValidator transactionValidator) {
        this.transactionValidator = transactionValidator;
    }

    @Override
    public void validateTransaction(Transaction transaction) throws IllegalArgumentException {
        transactionValidator.validate(transaction);
    }
}
