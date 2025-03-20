package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.AccountGroup;
import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.repository.AccountGroupRepository;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.AccountGroupServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class AccountGroupService implements AccountGroupServiceInterface {
    private AccountGroupRepository accountGroupRepository;
    private AccountRepository accountRepository;

    public AccountGroupService(AccountGroupRepository accountGroupRepository, AccountRepository accountRepository) {
        this.accountGroupRepository = accountGroupRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountGroup createAccountGroup(String name) {
        return this.accountGroupRepository.save(new AccountGroup(name));
    }

    @Override
    public void addToGroup(Long accountGroup, Long account) {
        Account findedAccount = accountRepository.findById(account).orElse(new OwnAccount());
        AccountGroup findedAccountGroup = accountGroupRepository.findById(accountGroup).orElse(new AccountGroup("default"));

        findedAccountGroup.addAccount(findedAccount);
        findedAccount.setAccountGroup(findedAccountGroup);

        this.accountGroupRepository.save(findedAccountGroup);
        this.accountRepository.save(findedAccount);
    }
}
