package com.example.FinanceApp.service.base;

import com.example.FinanceApp.entity.AccountGroup;
import org.springframework.stereotype.Service;

@Service
public interface AccountGroupServiceInterface {
    AccountGroup createAccountGroup(String name);

    void addToGroup(Long accountGroup, Long account);

}
