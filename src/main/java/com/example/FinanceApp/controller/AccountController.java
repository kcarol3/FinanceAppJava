package com.example.FinanceApp.controller;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceInterface accountService;

    public AccountController(AccountServiceInterface accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account createAccount(@RequestParam String type) {
        return accountService.createAndSaveAccount(type);
    }
}
