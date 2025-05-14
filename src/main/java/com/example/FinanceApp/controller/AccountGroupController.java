package com.example.FinanceApp.controller;

import com.example.FinanceApp.dto.AccountGroupDTO;
import com.example.FinanceApp.entity.AccountGroup;
import com.example.FinanceApp.repository.AccountGroupRepository;
import com.example.FinanceApp.service.base.AccountGroupServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-group")
public class AccountGroupController {
    private final AccountGroupServiceInterface accountGroupServiceInterface;
    private final AccountGroupRepository accountGroupRepository;

    public AccountGroupController(AccountGroupServiceInterface accountGroupServiceInterface, AccountGroupRepository accountGroupRepository) {
        this.accountGroupServiceInterface = accountGroupServiceInterface;
        this.accountGroupRepository = accountGroupRepository;
    }

    @PostMapping
    public AccountGroup createAccount(@RequestParam String name) {
        return accountGroupServiceInterface.createAccountGroup(name);
    }

    @PostMapping("/add")
    public void add(@RequestBody AccountGroupDTO accountGroupDTO) {
        accountGroupServiceInterface.addToGroup(accountGroupDTO.getAccountGroup(), accountGroupDTO.getAccount());
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam Long accountGroup) {
        AccountGroup foundGroup = accountGroupRepository.getById(accountGroup);
        return foundGroup.getFullBalance();
    }
}
