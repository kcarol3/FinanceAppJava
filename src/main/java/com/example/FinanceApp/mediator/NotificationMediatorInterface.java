package com.example.FinanceApp.mediator;

import org.springframework.stereotype.Component;

@Component
public interface NotificationMediatorInterface {
    void sendNotification(Application colleague, String message);

    void add(Application application);
}
