package com.example.FinanceApp.controller;

import com.example.FinanceApp.flyweight.BankInterface;
import com.example.FinanceApp.service.BankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public List<BankInterface> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{name}")
    public BankInterface getBankByName(@PathVariable String name) {
        return bankService.getBankByName(name);
    }
}