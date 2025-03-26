package com.example.FinanceApp.service;

import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.DeleteAccountInterface;
import org.springframework.stereotype.Service;

@Service
public class DeleteAccountService implements DeleteAccountInterface {
    private final AccountRepository accountRepository;

    public DeleteAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void deleteAccount(Long id, String username) {
        accountRepository.deleteById(id);
    }
}
