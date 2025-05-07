package com.example.FinanceApp.factory.op.transaction;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class RecurringTransactionCreator implements TransactionCreator {

    private final AccountRepository repository;

    public RecurringTransactionCreator(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(String type) {
        return "RECURRING".equalsIgnoreCase(type);
    }

    @Override
    public Transaction create(TransactionDTO dto) {
        Account account = repository.findById(dto.getAccount())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return new RecurringTransaction(dto.getAmount(), dto.getCurrency(), dto.getName(),
                dto.getDescription(), account, dto.getDate());
    }
}

