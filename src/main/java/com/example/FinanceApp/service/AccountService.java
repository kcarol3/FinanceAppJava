package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.factory.AccountFactory;
import com.example.FinanceApp.memento.AccountMemento;
import com.example.FinanceApp.repository.AccountMementoRepository;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService implements AccountServiceInterface {
    private final AccountFactory accountFactory;
    private final AccountRepository accountRepository;
    private final AccountMementoRepository accountMementoRepository;

    public AccountService(AccountFactory accountFactory, AccountRepository accountRepository, AccountMementoRepository accountMementoRepository) {
        this.accountFactory = accountFactory;
        this.accountRepository = accountRepository;
        this.accountMementoRepository = accountMementoRepository;
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

    @Override
    public AccountMemento createAndSaveAccountMemento(Account account) {
        AccountMemento memento = new AccountMemento();
        memento.setAccount(account);
        memento.setBalance(account.getBalance());
        account.addMementos(memento);
        return accountMementoRepository.save(memento);
    }

    @Override
    @Transactional
    public void restoreAccountState(Long accountId, Long mementoId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        AccountMemento memento = accountMementoRepository.findById(mementoId)
                .orElseThrow(() -> new RuntimeException("Memento not found"));

        account.restoreFromMemento(memento);
        accountRepository.save(account);
    }

    @Override
    public AccountMemento findFirstByAccountIdOrderByIdDesc(Long accountId) {
        return accountMementoRepository.findTopByAccountIdOrderByIdDesc(accountId);
    }

}
