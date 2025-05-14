package com.example.FinanceApp.factory.op.account;

import com.example.FinanceApp.entity.base.Account;

public interface AccountCreator {
    boolean supports(String type);

    Account create();
}

