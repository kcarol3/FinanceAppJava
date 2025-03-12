package com.example.FinanceApp.service.base;

import com.example.FinanceApp.entity.base.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountServiceInterface {
    public Account createAndSaveAccount(String type);
}
