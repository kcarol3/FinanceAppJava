package com.example.FinanceApp.factory.op.account;

import com.example.FinanceApp.entity.base.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;


// Tydzien 7, liskov 1, sprawdzenie zasady podstawienia liskov 1
public class AccountCreatorTest {
    @Test
    void testLiskovSubstitution() {
        // Tworzymy AccountCreator dla obu typów
        AccountCreator ownCreator = new OwnAccountCreator();
        AccountCreator savingsCreator = new SavingsAccountCreator();

        // Tworzymy konta
        Account account = ownCreator.create();
        assertNotNull(account);
        assertEquals(0.0, account.getBalance());
        assertEquals(0.0, account.getBalance());

        account = savingsCreator.create();
        assertNotNull(account);
        assertEquals(0.0, account.getBalance());
        assertEquals(0.0, account.getBalance());
    }
}
