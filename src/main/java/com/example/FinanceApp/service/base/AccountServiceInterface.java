package com.example.FinanceApp.service.base;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.memento.AccountMemento;
import org.springframework.stereotype.Service;

@Service
public interface AccountServiceInterface {
    Account createAndSaveAccount(String type);
    AccountMemento createAndSaveAccountMemento(Account account);
    void restoreAccountState(Long accountId, Long mementoId);
    AccountMemento findFirstByAccountIdOrderByIdDesc(Long accountId);
}
