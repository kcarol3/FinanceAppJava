package com.example.FinanceApp.proxy;

import com.example.FinanceApp.service.DeleteAccountService;
import com.example.FinanceApp.service.base.DeleteAccountInterface;
import org.springframework.stereotype.Service;

//Tydzień 3, Wzorzec Proxy 1, sprawdzanie, czy użytkownik ma uprawnienia do usunięcia konta
@Service
public class AccountServiceProxy implements DeleteAccountInterface {
    private final DeleteAccountService deleteAccountService;

    public AccountServiceProxy(DeleteAccountService deleteAccountService) {
        this.deleteAccountService = deleteAccountService;
    }

    @Override
    public void deleteAccount(Long id, String username) {
        if (!hasPermission(username)) {
            throw new SecurityException("You do not have permission to delete this account");
        } else {
            deleteAccountService.deleteAccount(id, username);
        }

    }

    private boolean hasPermission(String username) {
        return isAdmin(username);
    }

    private boolean isAdmin(String username) {
        return "admin".equals(username);
    }
}
//Koniec, Tydzień 3, Wzorzec Proxy 1