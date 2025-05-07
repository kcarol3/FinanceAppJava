package com.example.FinanceApp.factory.op.transaction;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.IncomeTransaction;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class IncomeTransactionCreator implements TransactionCreator {

    private final AccountRepository repository;

    public IncomeTransactionCreator(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean supports(String type) {
        return "INCOME".equalsIgnoreCase(type);
    }

    @Override
    public Transaction create(TransactionDTO dto) {
        Account account = repository.findById(dto.getAccount())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return new IncomeTransaction(dto.getAmount(), dto.getCurrency(), dto.getName(),
                dto.getDescription(), account, dto.getDate());
    }
}
