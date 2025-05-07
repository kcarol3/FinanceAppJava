package com.example.FinanceApp.factory;

import com.example.FinanceApp.config.AccountConfig;
import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.SavingsAccount;
import com.example.FinanceApp.entity.base.Account;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Supplier;

//Tydzień 6, SRP 3
//Tydzien 6, Open-closed, 1
@Component
public class AccountFactory {
    //Tydzień 1, Wzorzec Factory 1, tworzenie obiektów konta w zależności od typu
// oryginalna wersja

//    public Account createAccount(String type) {
//        return switch (type.toUpperCase()) {
//            case "SAVINGS" -> new SavingsAccount.Builder()
//                    .balance(AccountConfig.getInstance().getBalance())
//                    .interestRate(AccountConfig.getInstance().getInterestRate())
//                    .build();
//            case "OWN" -> new OwnAccount.Builder()
//                    .balance(AccountConfig.getInstance().getBalance())
//                    .build();
//            default -> throw new IllegalArgumentException("Unknown account type: " + type);
//        };
//    }
    //Koniec, Tydzień 1, Wzorzec Factory 1

    // abstrakacja
//    private final List<AccountCreator> creators;
//
//    public AccountFactory(List<AccountCreator> creators) {
//        this.creators = creators;
//    }
//
//    public Account createAccount(String type) {
//        return creators.stream()
//                .filter(c -> c.supports(type))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Unknown type: " + type))
//                .create();
//    }

    //sterowanie danymi
    private final Map<String, Supplier<Account>> accountStrategies = Map.of(
            "OWN", () -> new OwnAccount.Builder()
                    .balance(AccountConfig.getInstance().getBalance())
                    .build(),

            "SAVINGS", () -> new SavingsAccount.Builder()
                    .balance(AccountConfig.getInstance().getBalance())
                    .interestRate(AccountConfig.getInstance().getInterestRate())
                    .build()
    );

    public Account createAccount(String type) {
        Supplier<Account> creator = accountStrategies.get(type.toUpperCase());
        if (creator == null) {
            throw new IllegalArgumentException("Unknown account type: " + type);
        }
        return creator.get();
    }
}
//Tydzien 6, Open-closed, 1, koniec
//Tydzień 6, SRP 3, koniec
