package com.example.FinanceApp.factory.op.account;

import com.example.FinanceApp.config.AccountConfig;
import com.example.FinanceApp.entity.SavingsAccount;
import com.example.FinanceApp.entity.base.Account;
import org.springframework.stereotype.Component;

@Component
public class SavingsAccountCreator implements AccountCreator {
    public boolean supports(String type) {
        return "SAVINGS".equalsIgnoreCase(type);
    }

    public Account create() {
        return new SavingsAccount.Builder()
                .balance(AccountConfig.getInstance().getBalance())
                .interestRate(AccountConfig.getInstance().getInterestRate())
                .build();
    }
}