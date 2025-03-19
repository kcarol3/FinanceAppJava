package com.example.FinanceApp.factory;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.IncomeTransaction;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import org.springframework.stereotype.Component;

@Component
public class TransactionFactory {
    private AccountRepository repository;
    private AccountServiceInterface accountService;

    public TransactionFactory(AccountRepository repository, AccountServiceInterface accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public Transaction createAccount(String type, TransactionDTO transactionDto) {
        Account account = repository.findById(transactionDto.getAccount())
                .orElse(accountService.createAndSaveAccount("OWN"));
        return switch (type.toUpperCase()) {
            case "EXPENSE" -> new ExpenseTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
            case "INCOME" -> new IncomeTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
            case "RECURRING" -> new RecurringTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
            default -> throw new IllegalArgumentException("Unknown transaction type: " + type);
        };
    }
}
