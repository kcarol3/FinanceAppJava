package com.example.FinanceApp.mediator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Tydzien 4, wzorzec Mediator 1, implementacja mediatora, wysylajaca powiadomienia
@Component
public class NotificationMediator implements NotificationMediatorInterface {
    private List<Application> applicationRegister;

    public NotificationMediator() {
        this.applicationRegister = new ArrayList<>();
    }
//Tydzień 9, stream processing 1
    @Override
    public void sendNotification(Application application, String message) {
        applicationRegister.stream()
                .filter(app -> app != application)
                .forEach(app -> app.notifyUser(message));
    }
//Koniec, Tydzień 9, stream processing 1
    public void add(Application application) {
        applicationRegister.add(application);
    }
}
// Tydzien 4, wzorzec mediator 1, koniec
