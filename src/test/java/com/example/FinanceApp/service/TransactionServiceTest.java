package com.example.FinanceApp.service;

import com.example.FinanceApp.adapter.DateFormatTimeOptionalAdapter;
import com.example.FinanceApp.adapter.ToPlnAdapter;
import com.example.FinanceApp.decorator.TransactionValidationException;
import com.example.FinanceApp.decorator.TransactionValidator;
import com.example.FinanceApp.dto.TransactionDTO;
import com.example.FinanceApp.entity.ExpenseTransaction;
import com.example.FinanceApp.entity.IncomeTransaction;
import com.example.FinanceApp.entity.RecurringTransaction;
import com.example.FinanceApp.entity.SavingsAccount;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.entity.base.Transaction;
import com.example.FinanceApp.factory.TransactionFactory;
import com.example.FinanceApp.observer.TransactionAddedEvent;
import com.example.FinanceApp.repository.TransactionRepository;
import com.example.FinanceApp.service.base.AccountServiceInterface;
import com.example.FinanceApp.service.transaction.TransactionService;
import com.example.FinanceApp.state.TransactionContext;
import com.example.FinanceApp.strategy.taxStrategy.TaxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock private TransactionFactory transactionFactory;
    @Mock private TransactionRepository transactionRepository;
    @Mock private ToPlnAdapter toPlnAdapter;
    @Mock private DateFormatTimeOptionalAdapter dateFormatAdapter;
    @Mock private AccountServiceInterface accountService;
    @Mock private ApplicationEventPublisher publisher;
    @Mock private TaxService taxService;
    @Mock private TransactionValidator transactionValidator;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        // No setup required for now
    }


    @Test
    void testCreateAndSaveIncomeTransaction() {
        TransactionDTO dto = new TransactionDTO();
        dto.setAmount(1000.0);
        dto.setCurrency("USD");
        dto.setDateString("2024-01-01T10:00");

        Transaction mockTransaction = new IncomeTransaction();
        mockTransaction.setAmount(1000.0);
        mockTransaction.setCurrency("USD");
        Account account = new SavingsAccount();
        account.setId(1L);
        account.setBalance(1000.0);
        mockTransaction.setAccount(account);

        when(toPlnAdapter.convert(anyDouble(), eq("USD"))).thenReturn(1000.0);
        when(dateFormatAdapter.convertDate(anyString())).thenReturn(LocalDateTime.now());
        when(transactionFactory.createTransaction(eq("INCOME"), any())).thenReturn(mockTransaction);

        Transaction transaction = transactionService.createAndSaveTransaction("INCOME", dto);

        assertEquals(1000.0, transaction.getAmount());
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void testExpenseTransactionFailsWhenInsufficientBalance() {
        TransactionDTO dto = new TransactionDTO();
        dto.setAmount(900.0);
        dto.setCurrency("PLN");
        dto.setDateString("2024-01-01T10:00");

        Account account = new SavingsAccount();
        account.setBalance(900.0);

        Transaction mockTransaction = new ExpenseTransaction();
        mockTransaction.setAmount(900.0);
        mockTransaction.setAccount(account);

        when(toPlnAdapter.convert(anyDouble(), eq("PLN"))).thenReturn(900.0);
        when(dateFormatAdapter.convertDate(anyString())).thenReturn(LocalDateTime.now());
        when(transactionFactory.createTransaction(eq("EXPENSE"), any())).thenReturn(mockTransaction);

        assertThrows(TransactionValidationException.class, () -> {
            transactionService.createAndSaveTransaction("EXPENSE", dto);
        });
    }

    @Test
    void testCalculateTotalExpenses() {
        Long accountId = 1L;

        Account account = new SavingsAccount();
        account.setId(accountId);

        Transaction t1 = new ExpenseTransaction(); t1.setAmount(100.0); t1.setAccount(account);
        Transaction t2 = new ExpenseTransaction(); t2.setAmount(200.0); t2.setAccount(account);

        when(transactionRepository.findAll()).thenReturn(List.of(t1, t2));

        double total = transactionService.calculateTotalExpenses(accountId);

        assertEquals(200.0, total);
    }

}
