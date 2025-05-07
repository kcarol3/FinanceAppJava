package com.example.FinanceApp.factory.op.account;

import com.example.FinanceApp.config.AccountConfig;
import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.base.Account;
import org.springframework.stereotype.Component;

@Component
public class OwnAccountCreator implements AccountCreator {
    public boolean supports(String type) {
        return "OWN".equalsIgnoreCase(type);
    }

    public Account create() {
        return new OwnAccount.Builder()
                .balance(AccountConfig.getInstance().getBalance())
                .build();
    }
}

