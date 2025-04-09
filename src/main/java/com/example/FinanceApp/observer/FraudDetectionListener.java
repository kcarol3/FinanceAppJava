package com.example.FinanceApp.observer;

import com.example.FinanceApp.entity.base.Transaction;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FraudDetectionListener {
    private static final Double FRAUD_THRESHOLD = new Double("10000");

    @EventListener
    public void detectFraud(TransactionAddedEvent event) {
        Transaction transaction = event.getTransaction();

        if (isSuspicious(transaction)) {
            System.out.println("🚨 Podejrzana transakcja wykryta!");
            System.out.println("🔍 Info: " + transaction);
        }
    }

    private boolean isSuspicious(Transaction transaction) {
        return transaction.getAmount().compareTo(FRAUD_THRESHOLD) > 0;
    }
}
