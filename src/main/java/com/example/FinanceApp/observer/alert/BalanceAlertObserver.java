package com.example.FinanceApp.observer.alert;

import com.example.FinanceApp.entity.Alert;
import com.example.FinanceApp.entity.base.Account;
import com.example.FinanceApp.repository.AlertRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// Tydzien 5, wzorzec observer 2, implementacja obserwatora, umozliwiajaca zapisywanie powiadomien do bazy przy transaferach
@Component
public class BalanceAlertObserver implements Observer {

    private final AlertRepository alertRepository;

    public BalanceAlertObserver(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public void onBalanceChanged(Account account, Double amount, String reason) {
        String message;

        if (amount < 0) {
            message = "Ubyło " + Math.abs(amount) + " z konta ID " + account.getId() + ". Powód: " + reason;
        } else {
            message = "Dodano " + amount + " do konta ID " + account.getId() + ". Powód: " + reason;
        }

        Alert alert = new Alert(message, LocalDateTime.now(), account);
        alertRepository.save(alert);
    }
}

// Tydzien 5, wzorzec observer 2, koniec
