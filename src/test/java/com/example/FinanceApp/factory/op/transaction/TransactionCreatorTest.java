package com.example.FinanceApp.factory.op.transaction;

import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.factory.op.transaction.ExpenseTransactionCreator;
import com.example.FinanceApp.factory.op.transaction.IncomeTransactionCreator;
import com.example.FinanceApp.factory.op.transaction.TransactionCreator;
import com.example.FinanceApp.repository.AccountRepository;
import com.example.FinanceApp.state.TransactionStateType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


// tydzien 7, liskov 2
public class TransactionCreatorTest {
    @Test
    void testLiskovSubstitution() {
        // Tworzymy mocka AccountRepository
        AccountRepository accountRepository = mock(AccountRepository.class);
        Account mockAccount = mock(Account.class);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(mockAccount));

        // Tworzymy TransactionDTO
        TransactionDTO dto = new TransactionDTO();
        dto.setId(2L);
        dto.setAmount(150.0);
        dto.setCurrency("USD");
        dto.setName("Test Transaction");
        dto.setUUID("9876-5432-1011");
        dto.setDescription("Test Description");
        dto.setAccount(1L);
        dto.setDateString("2025-05-07T15:00:00");
        dto.setDate(LocalDateTime.parse("2025-05-07T15:00:00"));
        dto.setType("INCOME");
        dto.setState(TransactionStateType.COMPLETED);
        // Tworzymy oba TransactionCreator
        TransactionCreator expenseCreator = new ExpenseTransactionCreator(accountRepository);
        TransactionCreator incomeCreator = new IncomeTransactionCreator(accountRepository);

        // Tworzymy transakcje
        Transaction transaction = expenseCreator.create(dto);

        assertNotNull(transaction);
        assertEquals(150.0, transaction.getAmount());
        assertEquals("USD", transaction.getCurrency());
        assertEquals("Test Transaction", transaction.getName());
        assertEquals("Test Description", transaction.getDescription());
        assertEquals(mockAccount, transaction.getAccount());
        assertNotNull(transaction);

        transaction = incomeCreator.create(dto);
        assertEquals(150.0, transaction.getAmount());
        assertEquals("USD", transaction.getCurrency());
        assertEquals("Test Transaction", transaction.getName());
        assertEquals("Test Description", transaction.getDescription());
        assertEquals(mockAccount, transaction.getAccount());
    }
}
