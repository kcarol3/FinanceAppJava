package com.example.FinanceApp.flyweight.transactionIcon.op;

import org.springframework.stereotype.Component;

@Component("EXPENSE")
public class ExpenseMetadata implements TransactionTypeMetadata {
    @Override
    public String getIconPath() {
        return "src/main/resources/expense-icon.png";
    }
}
