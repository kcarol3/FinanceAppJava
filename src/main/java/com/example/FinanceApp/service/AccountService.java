package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.factory.AccountFactory;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements AccountServiceInterface {
    private final AccountFactory accountFactory;
    private final AccountRepository accountRepository;

    public AccountService(AccountFactory accountFactory, AccountRepository accountRepository) {
        this.accountFactory = accountFactory;
        this.accountRepository = accountRepository;
    }
    @Override
    @Transactional
    public Account createAndSaveAccount(String type) {
        Account account = accountFactory.createAccount(type);

        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void transferMoney(Long fromId, Long toId, Double amount) {
        Account fromAccount = accountRepository.findById(fromId)
                .orElseThrow(() -> new RuntimeException("Konto źródłowe nie znalezione"));
        Account toAccount = accountRepository.findById(toId)
                .orElseThrow(() -> new RuntimeException("Konto docelowe nie znalezione"));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Niewystarczające środki");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
