package com.example.FinanceApp.flyweight.transactionIcon.op;

import org.springframework.stereotype.Component;

@Component("INCOME")
public class IncomeMetadata implements TransactionTypeMetadata {
    @Override
    public String getIconPath() {
        return "src/main/resources/income-icon.png";
    }
}
