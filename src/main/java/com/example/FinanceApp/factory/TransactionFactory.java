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

import java.util.Optional;

@Component
public class TransactionFactory {
    private AccountRepository repository;
    private AccountServiceInterface accountService;

    //Tydzień 1, Wzorzec Factory 2, tworzenie obiektów kont w zależności od typu
    public TransactionFactory(AccountRepository repository, AccountServiceInterface accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public Transaction createAccount(String type, TransactionDTO transactionDto) {
        Optional<Account> accountOptional = repository.findById(transactionDto.getAccount());
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            return switch (type.toUpperCase()) {
                case "EXPENSE" ->
                        new ExpenseTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
                case "INCOME" ->
                        new IncomeTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
                case "RECURRING" ->
                        new RecurringTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
                default -> throw new IllegalArgumentException("Unknown transaction type: " + type);
            };
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }
    //Koniec, Tydzień 1, Wzorzec Factory 2
}
