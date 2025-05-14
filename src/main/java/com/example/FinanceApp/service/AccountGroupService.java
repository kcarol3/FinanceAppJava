package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.AccountGroup;
import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.repository.AccountGroupRepository;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.AccountGroupService.AccountGroupCreatorServiceInterface;
import com.example.FinanceApp.service.base.AccountGroupServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class AccountGroupService implements AccountGroupCreatorServiceInterface {
    private AccountGroupRepository accountGroupRepository;

    public AccountGroupService(AccountGroupRepository accountGroupRepository) {
        this.accountGroupRepository = accountGroupRepository;
    }

    @Override
    public AccountGroup createAccountGroup(String name) {
        return this.accountGroupRepository.save(new AccountGroup(name));
    }
}
