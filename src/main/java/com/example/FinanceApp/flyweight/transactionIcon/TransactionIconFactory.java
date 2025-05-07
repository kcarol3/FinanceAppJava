package com.example.FinanceApp.flyweight.transactionIcon;

import com.example.FinanceApp.flyweight.transactionIcon.op.TransactionTypeMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


// Tydzien 6, Open-closed 5
@Component
public class TransactionIconFactory {
    // oryginal
//    private final Map<String, TransactionIconInterface> iconCache = new ConcurrentHashMap<>();
//
//    public TransactionIconInterface getTransactionIcon(String transactionType) throws IOException {
//        String iconPath = getIconPathForTransactionType(transactionType);
//
//        return iconCache.computeIfAbsent(iconPath, path -> {
//            try {
//                return new TransactionIcon(path);
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to load image: " + path, e);
//            }
//        });
//    }
//    private String getIconPathForTransactionType(String transactionType) {
//        return switch (transactionType.toUpperCase()) {
//            case "EXPENSE" -> "src/main/resources/expense-icon.png";
//            case "INCOME" -> "src/main/resources/income-icon.png";
//            default -> throw new IllegalArgumentException("Unknown transaction type: " + transactionType);
//        };
//    }

    // sterowanie dadnymi

//    private final Map<String, TransactionIconInterface> iconCache = new ConcurrentHashMap<>();
//
//    private final Map<String, String> iconPathMap = Map.of(
//            "EXPENSE", "src/main/resources/expense-icon.png",
//            "INCOME", "src/main/resources/income-icon.png"
//    );
//
//    public TransactionIconInterface getTransactionIcon(String transactionType) {
//        String upperType = transactionType.toUpperCase();
//        String iconPath = iconPathMap.get(upperType);
//        if (iconPath == null) {
//            throw new IllegalArgumentException("Unknown transaction type: " + transactionType);
//        }
//
//        return iconCache.computeIfAbsent(iconPath, path -> {
//            try {
//                return new TransactionIcon(path);
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to load image: " + path, e);
//            }
//        });
//    }

    // abstrakcja
    private final Map<String, TransactionTypeMetadata> metadataMap;
    private final Map<String, TransactionIconInterface> iconCache = new ConcurrentHashMap<>();

    @Autowired
    public TransactionIconFactory(Map<String, TransactionTypeMetadata> metadataMap) {
        this.metadataMap = metadataMap;
    }

    public TransactionIconInterface getTransactionIcon(String transactionType) {
        TransactionTypeMetadata metadata = metadataMap.get(transactionType.toUpperCase());
        if (metadata == null) {
            throw new IllegalArgumentException("No metadata for type: " + transactionType);
        }

        String path = metadata.getIconPath();

        return iconCache.computeIfAbsent(path, p -> {
            try {
                return new TransactionIcon(p);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load icon: " + p, e);
            }
        });
    }
}

// Tydzien 6, Open-closed 5, koniec
