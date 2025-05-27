package com.example.FinanceApp.aspect;

import com.example.FinanceApp.repository.AccountGroupRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Tydzień 10, aspekt 4
@Aspect
@Component
public class AccountGroupExistenceCheckAspect {

    private final AccountGroupRepository repository;

    public AccountGroupExistenceCheckAspect(AccountGroupRepository repository) {
        this.repository = repository;
    }

    @Before("execution(* com.example.FinanceApp.service.base.AccountGroupService.AccountGroupCreatorServiceInterface.createAccountGroup(..)) && args(name)")
    public void checkIfAccountGroupExists(String name) {
        if (repository.existsAccountGroupByName(name)) {
            throw new IllegalArgumentException("Grupa kont o tej nazwie już istnieje.");
        }
    }
}
//Koniec, Tydzień 10, aspekt 4