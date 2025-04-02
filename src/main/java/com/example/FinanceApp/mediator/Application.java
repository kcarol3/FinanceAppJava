package com.example.FinanceApp.mediator;

public abstract class Application {
    protected String email;
    protected NotificationMediatorInterface mediator;

    public Application(NotificationMediatorInterface mediator, String email) {
        this.mediator = mediator;
        this.email = email;
    }

    public abstract void notifyUser(String message);
}
