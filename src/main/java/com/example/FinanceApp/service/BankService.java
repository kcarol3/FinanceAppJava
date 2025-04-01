package com.example.FinanceApp.service;

import com.example.FinanceApp.flyweight.Bank;
import com.example.FinanceApp.flyweight.BankFactory;
import com.example.FinanceApp.flyweight.BankInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankService {
    private final BankFactory bankFactory;

    public BankService(BankFactory bankFactory) {
        this.bankFactory = bankFactory;
    }

    public List<BankInterface> getAllBanks() {
        List<BankInterface> banks = new ArrayList<>();
        banks.add(bankFactory.getBank("Bank A", "BANKA123"));
        banks.add(bankFactory.getBank("Bank B", "BANKB456"));
        banks.add(bankFactory.getBank("Bank C", "BANKC789"));
        return banks;
    }

    public BankInterface getBankByName(String name) {
        return switch (name.toLowerCase()) {
            case "bank a" -> bankFactory.getBank("Bank A", "BANKA123");
            case "bank b" -> bankFactory.getBank("Bank B", "BANKB456");
            case "bank c" -> bankFactory.getBank("Bank C", "BANKC789");
            default -> null;
        };
    }
}
