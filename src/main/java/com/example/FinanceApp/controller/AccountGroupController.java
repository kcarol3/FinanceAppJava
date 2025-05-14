package com.example.FinanceApp.controller;

import com.example.FinanceApp.dto.AccountGroupDTO;
import com.example.FinanceApp.entity.AccountGroup;
import com.example.FinanceApp.repository.AccountGroupRepository;
import com.example.FinanceApp.service.base.AccountGroupService.AccountGroupCreatorServiceInterface;
import com.example.FinanceApp.service.base.AccountGroupService.AccountGroupMemberManagerServiceInterface;
import com.example.FinanceApp.service.base.AccountGroupServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account-group")
public class AccountGroupController {
    private final AccountGroupMemberManagerServiceInterface accountGroupMemberManagerService;
    private final AccountGroupRepository accountGroupRepository;
    private final AccountGroupCreatorServiceInterface accountGroupCreatorService;

    public AccountGroupController(AccountGroupMemberManagerServiceInterface accountGroupMemberManagerService, AccountGroupRepository accountGroupRepository, AccountGroupCreatorServiceInterface accountGroupCreatorService) {
        this.accountGroupMemberManagerService = accountGroupMemberManagerService;
        this.accountGroupRepository = accountGroupRepository;
        this.accountGroupCreatorService = accountGroupCreatorService;
    }

    @PostMapping
    public AccountGroup createAccount(@RequestParam String name) {
        return accountGroupCreatorService.createAccountGroup(name);
    }

    @PostMapping("/add")
    public void add(@RequestBody AccountGroupDTO accountGroupDTO) {
        accountGroupMemberManagerService.addToGroup(accountGroupDTO.getAccountGroup(), accountGroupDTO.getAccount());
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam Long accountGroup) {
        AccountGroup findedGroup = accountGroupRepository.getById(accountGroup);
        return findedGroup.getFullBalance();
    }
}
