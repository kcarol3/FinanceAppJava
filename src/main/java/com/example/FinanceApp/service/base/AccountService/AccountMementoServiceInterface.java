package com.example.FinanceApp.service.base.AccountService;

import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.memento.AccountMemento;

//Tydzień 7, ISP 2
public interface AccountMementoServiceInterface {
    AccountMemento createAndSaveAccountMemento(Account account);

    void restoreAccountState(Long accountId, Long mementoId);

    AccountMemento findFirstByAccountIdOrderByIdDesc(Long accountId);
}
//Tydzień 7, ISP 2, koniec