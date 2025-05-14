package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.AccountGroup;
import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.repository.AccountGroupRepository;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.base.AccountGroupService.AccountGroupMemberManagerServiceInterface;
import org.springframework.stereotype.Service;

//tydzien 7, IS 2, uzycie
@Service
public class AccountGroupMemberService implements AccountGroupMemberManagerServiceInterface {
    private AccountGroupRepository accountGroupRepository;
    private AccountRepository accountRepository;

    public AccountGroupMemberService(AccountGroupRepository accountGroupRepository, AccountRepository accountRepository) {
        this.accountGroupRepository = accountGroupRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void addToGroup(Long accountGroup, Long account) {
        Account findedAccount = accountRepository.findById(account).orElse(new OwnAccount());
        AccountGroup findedAccountGroup = accountGroupRepository.findById(accountGroup).orElse(new AccountGroup("default"));

        this.saveAccountToAccountGroup(findedAccount, findedAccountGroup);
    }

    private void saveAccountToAccountGroup(Account findedAccount, AccountGroup findedAccountGroup) {
        findedAccountGroup.addAccount(findedAccount);
        findedAccount.setAccountGroup(findedAccountGroup);

        this.accountGroupRepository.save(findedAccountGroup);
        this.accountRepository.save(findedAccount);
    }
}

