package com.example.FinanceApp.dto;

import com.example.FinanceApp.entity.AccountGroup;
import com.example.FinanceApp.entity.base.Account;

public class AccountGroupDTO {
    Long accountGroup;
    Long account;
    public AccountGroupDTO(){}

    public AccountGroupDTO(Long accountGroup, Long account) {
        this.accountGroup = accountGroup;
        this.account = account;
    }

    public Long getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(Long accountGroup) {
        this.accountGroup = accountGroup;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }
}
