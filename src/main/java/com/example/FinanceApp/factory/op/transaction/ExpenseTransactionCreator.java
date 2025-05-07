package com.example.FinanceApp.factory.op.transaction;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class ExpenseTransactionCreator implements TransactionCreator {
    private final AccountRepository repository;

    public ExpenseTransactionCreator(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(String type) {
        return "EXPENSE".equalsIgnoreCase(type);
    }

    @Override
    public Transaction create(TransactionDTO dto) {
        return new ExpenseTransaction(dto.getAmount(), dto.getCurrency(), dto.getName(), dto.getDescription(), findAccount(dto), dto.getDate());
    }

    private Account findAccount(TransactionDTO dto) {
        return repository.findById(dto.getAccount())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}

