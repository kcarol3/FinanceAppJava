package com.example.FinanceApp.controller;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.proxy.AccountServiceProxy;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import com.example.FinanceApp.service.base.LoggerFacadeInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountServiceInterface accountService;
    private final LoggerFacadeInterface logger;
    private final AccountServiceProxy accountServiceProxy;

    public AccountController(AccountServiceInterface accountService, LoggerFacadeInterface logger, AccountServiceProxy accountServiceProxy) {
        this.accountService = accountService;
        this.logger = logger;
        this.accountServiceProxy = accountServiceProxy;
    }

    @PostMapping
    public Account createAccount(@RequestParam String type) {
        logger.log("Create account type: " + type + "\n");
        return accountService.createAndSaveAccount(type);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        try {
            accountServiceProxy.deleteAccount(id, "admin");
            return "Deleted account with id: " + id + "\n";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/restore/{accountId}")
    public String restoreAccountState(@PathVariable Long accountId) {
        try {
            accountService.restoreAccountState(accountId, accountService.findFirstByAccountIdOrderByIdDesc(accountId).getId());
            return "Account state with ID " + accountId + " has been restored to the previous state.";
        } catch (Exception e) {
            return "Error restoring account state: " + e.getMessage();
        }
    }
}
