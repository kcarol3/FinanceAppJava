package com.example.FinanceApp.icon;

import com.example.FinanceApp.flyweight.transactionIcon.op.ExpenseMetadata;
import com.example.FinanceApp.flyweight.transactionIcon.op.IncomeMetadata;
import com.example.FinanceApp.flyweight.transactionIcon.op.TransactionTypeMetadata;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// tydzien 7, liskov 5
@ExtendWith(MockitoExtension.class)
public class TransactionIconTest {
    private IncomeMetadata incomeMetadata;
    private ExpenseMetadata expenseMetadata;

    @BeforeEach
    void setUp() throws IOException {
        incomeMetadata = new IncomeMetadata();
        expenseMetadata = new ExpenseMetadata();
    }
    @Test
    void shouldFollowLiskovSubstitutionPrinciple() {
        TransactionTypeMetadata incomeMeta = incomeMetadata;
        TransactionTypeMetadata expenseMeta = expenseMetadata;

        assertEquals("src/main/resources/income-icon.png", incomeMeta.getIconPath());
        assertEquals("src/main/resources/expense-icon.png", expenseMeta.getIconPath());

        assertTrue(expenseMeta instanceof TransactionTypeMetadata);
        assertTrue(incomeMeta instanceof TransactionTypeMetadata);
    }
}
