package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.factory.AccountFactory;
import com.example.FinanceApp.memento.AccountMemento;
import com.example.FinanceApp.observer.alert.Observer;
import com.example.FinanceApp.observer.alert.Subject;
import com.example.FinanceApp.repository.AccountMementoRepository;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Service
public class AccountService implements AccountServiceInterface, Subject {
    private final AccountFactory accountFactory;
    private final AccountRepository accountRepository;
    private final AccountMementoRepository accountMementoRepository;

    @Autowired
    public AccountService(AccountFactory accountFactory, AccountRepository accountRepository, AccountMementoRepository accountMementoRepository, List<Observer> observers) {
        this.accountFactory = accountFactory;
        this.accountRepository = accountRepository;
        this.accountMementoRepository = accountMementoRepository;
        this.observers = observers;
    }

    private List<Observer> observers;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Account account, Double amount, String reason) {
        for (Observer observer : observers) {
            observer.onBalanceChanged(account, amount, reason);
        }
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

        notifyObservers(fromAccount, -amount, "Przelew wychodzący do konta ID " + toId);
        notifyObservers(toAccount, amount, "Przelew przychodzący od konta ID " + fromId);
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
