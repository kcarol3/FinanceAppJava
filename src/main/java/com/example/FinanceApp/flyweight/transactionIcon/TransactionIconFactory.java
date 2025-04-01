package com.example.FinanceApp.flyweight.transactionIcon;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TransactionIconFactory {
    private final Map<String, TransactionIconInterface> iconCache = new ConcurrentHashMap<>();

    public TransactionIconInterface getTransactionIcon(String transactionType) throws IOException {
        String iconPath = getIconPathForTransactionType(transactionType);

        return iconCache.computeIfAbsent(iconPath, path -> {
            try {
                return new TransactionIcon(path);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load image: " + path, e);
            }
        });
    }

    private String getIconPathForTransactionType(String transactionType) {
        return switch (transactionType.toUpperCase()) {
            case "EXPENSE" -> "src/main/resources/expense-icon.png";
            case "INCOME" -> "src/main/resources/income-icon.png";
            default -> throw new IllegalArgumentException("Unknown transaction type: " + transactionType);
        };
    }
}
