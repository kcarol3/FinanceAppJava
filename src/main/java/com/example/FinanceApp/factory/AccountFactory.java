package com.example.FinanceApp.factory;

import com.example.FinanceApp.config.AccountConfig;
import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.SavingsAccount;
import com.example.FinanceApp.entity.base.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory {

    public Account createAccount(String type) {
        return switch (type.toUpperCase()) {
            case "SAVINGS" -> new SavingsAccount.Builder()
                    .balance(AccountConfig.getInstance().getBalance())
                    .interestRate(AccountConfig.getInstance().getInterestRate())
                    .build();
            case "OWN" -> new OwnAccount.Builder()
                    .balance(AccountConfig.getInstance().getBalance())
                    .build();
            default -> throw new IllegalArgumentException("Unknown account type: " + type);
        };
    }
}
