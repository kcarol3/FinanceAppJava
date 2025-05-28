package com.example.FinanceApp;

import com.example.FinanceApp.entity.OwnAccount;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.factory.AccountFactory;
import com.example.FinanceApp.memento.AccountMemento;
import com.example.FinanceApp.observer.alert.Observer;
import com.example.FinanceApp.repository.AccountMementoRepository;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock private AccountFactory accountFactory;
    @Mock private AccountRepository accountRepository;
    @Mock private AccountMementoRepository accountMementoRepository;
    @Mock private Observer observer;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountRepository = Mockito.mock(AccountRepository.class);
        accountMementoRepository = Mockito.mock(AccountMementoRepository.class);
        accountService = new AccountService(accountFactory, accountRepository, accountMementoRepository, new ArrayList<>());
        accountService.registerObserver(observer);
    }

    @Test
    void shouldCreateAndSaveAccount() {
        OwnAccount account = new OwnAccount.Builder()
                .balance(0.0)
                .build();

        Mockito.when(accountFactory.createAccount("OWN")).thenReturn(account);
        Mockito.when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.createAndSaveAccount("OWN");

        Assertions.assertEquals(account, result);
        Mockito.verify(accountFactory).createAccount("OWN");
        Mockito.verify(accountRepository).save(account);
    }

    @Test
    void shouldTransferMoneySuccessfully() {
        OwnAccount fromAccount = new OwnAccount.Builder()
                .balance(500.0)
                .build();
        fromAccount.setId(1L);

        OwnAccount toAccount = new OwnAccount.Builder()
                .balance(100.0)
                .build();
        toAccount.setId(2L);

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        accountService.transferMoney(1L, 2L, 200.0);

        Assertions.assertEquals(300.0, fromAccount.getBalance());
        Assertions.assertEquals(300.0, toAccount.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenInsufficientFunds() {
        OwnAccount fromAccount = new OwnAccount.Builder()
                .balance(100.0)
                .build();
        fromAccount.setId(1L);

        OwnAccount toAccount = new OwnAccount.Builder()
                .balance(50.0)
                .build();
        toAccount.setId(2L);

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(fromAccount));
        Mockito.when(accountRepository.findById(2L)).thenReturn(Optional.of(toAccount));

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> accountService.transferMoney(1L, 2L, 200.0));

        Assertions.assertEquals("Niewystarczające środki na koncie ID 1", exception.getMessage());
    }

    @Test
    void shouldCreateAndSaveAccountMemento() {
        OwnAccount account = new OwnAccount.Builder()
                .balance(1000.0)
                .build();
        account.setId(1L);

        Mockito.when(accountMementoRepository.save(Mockito.any(AccountMemento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AccountMemento memento = accountService.createAndSaveAccountMemento(account);

        Assertions.assertEquals(account, memento.getAccount());
        Assertions.assertEquals(account.getBalance(), memento.getBalance());
    }

    @Test
    void shouldRestoreAccountState() {
        OwnAccount account = new OwnAccount.Builder()
                .balance(1000.0)
                .build();
        account.setId(1L);

        Mockito.when(accountMementoRepository.save(Mockito.any(AccountMemento.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AccountMemento memento = accountService.createAndSaveAccountMemento(account);

        account.withdraw(200.0);

        Assertions.assertEquals(1000.0, memento.getBalance());

    }
}