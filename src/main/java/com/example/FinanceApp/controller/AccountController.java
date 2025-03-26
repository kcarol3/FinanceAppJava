package com.example.FinanceApp.controller;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import com.example.FinanceApp.service.base.LoggerFacadeInterface;
import com.example.FinanceApp.service.log.LoggerFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountServiceInterface accountService;
    private final LoggerFacadeInterface logger;

    public AccountController(AccountServiceInterface accountService, LoggerFacadeInterface logger) {
        this.accountService = accountService;
        this.logger = logger;
    }

    @PostMapping
    public Account createAccount(@RequestParam String type) {
        logger.log("Create account type: " + type + "\n");
        return accountService.createAndSaveAccount(type);
    }
}
