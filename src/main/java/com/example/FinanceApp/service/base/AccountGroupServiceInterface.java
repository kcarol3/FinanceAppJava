package com.example.FinanceApp.service.base;

import com.example.FinanceApp.entity.AccountGroup;
import org.springframework.stereotype.Service;

//tydzien 7, dependency inversion 9
@Service
public interface AccountGroupServiceInterface {
    AccountGroup createAccountGroup(String name);

    void addToGroup(Long accountGroup, Long account);

}
