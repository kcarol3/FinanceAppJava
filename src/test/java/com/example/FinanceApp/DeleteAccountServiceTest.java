package com.example.FinanceApp;

import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.DeleteAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class DeleteAccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private DeleteAccountService deleteAccountService;

    @Test
    void shouldDeleteAccountById() {
        Long accountId = 1L;
        String username = "testUser";

        deleteAccountService.deleteAccount(accountId, username);

        Mockito.verify(accountRepository).deleteById(accountId);
    }
}

