package com.example.FinanceApp.factory;

import com.example.FinanceApp.config.AccountConfig;
import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.*;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.factory.op.transaction.TransactionCreator;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;

// Tydzien 6, Open-closed 2
@Component
public class TransactionFactory {
    private AccountRepository repository;
    private AccountServiceInterface accountService;

    //Tydzień 1, Wzorzec Factory 2, tworzenie obiektów kont w zależności od typu
//    public TransactionFactory(AccountRepository repository, AccountServiceInterface accountService) {
//        this.repository = repository;
//        this.accountService = accountService;
//    }

    // oryginal
//    public Transaction createAccount(String type, TransactionDTO transactionDto) {
//        Optional<Account> accountOptional = repository.findById(transactionDto.getAccount());
//        if (accountOptional.isPresent()) {
//            Account account = accountOptional.get();
//            return switch (type.toUpperCase()) {
//                case "EXPENSE" ->
//                        new ExpenseTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
//                case "INCOME" ->
//                        new IncomeTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
//                case "RECURRING" ->
//                        new RecurringTransaction(transactionDto.getAmount(), transactionDto.getCurrency(), transactionDto.getName(), transactionDto.getDescription(), account, transactionDto.getDate());
//                default -> throw new IllegalArgumentException("Unknown transaction type: " + type);
//            };
//        } else {
//            throw new IllegalArgumentException("Account not found");
//        }
//    }
    //Koniec, Tydzień 1, Wzorzec Factory 2

    // abstrakcja
//    private final List<TransactionCreator> creators;
//
//    public TransactionFactory(AccountRepository repository, AccountServiceInterface accountService, List<TransactionCreator> creators) {
//        this.repository = repository;
//        this.accountService = accountService;
//        this.creators = creators;
//    }
//  //Tydzień 9, stream processing 2
//    public Transaction createTransaction(String type, TransactionDTO dto) {
//        return creators.stream()
//                .filter(c -> c.supports(type))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Unknown transaction type: " + type))
//                .create(dto);
//    }
//  //Koniec, Tydzień 9, stream processing

    // sterowanie danymi
    private final Map<String, BiFunction<TransactionDTO, Account, Transaction>> transactionStrategies;

    public TransactionFactory(AccountRepository accountRepository) {
        this.repository = accountRepository;

        this.transactionStrategies = Map.of(
                "EXPENSE", (dto, acc) -> new ExpenseTransaction(dto.getAmount(), dto.getCurrency(), dto.getName(), dto.getDescription(), acc, dto.getDate()),
                "INCOME", (dto, acc) -> new IncomeTransaction(dto.getAmount(), dto.getCurrency(), dto.getName(), dto.getDescription(), acc, dto.getDate()),
                "RECURRING", (dto, acc) -> new RecurringTransaction(dto.getAmount(), dto.getCurrency(), dto.getName(), dto.getDescription(), acc, dto.getDate())
        );
    }

    public Transaction createTransaction(String type, TransactionDTO dto) {
        BiFunction<TransactionDTO, Account, Transaction> creator = transactionStrategies.get(type.toUpperCase());

        if (creator == null) {
            throw new IllegalArgumentException("Unknown transaction type: " + type);
        }

        Account account = repository.findById(dto.getAccount())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        return creator.apply(dto, account);
    }

    // Tydzien 6, Open-closed 2, koniec
}
