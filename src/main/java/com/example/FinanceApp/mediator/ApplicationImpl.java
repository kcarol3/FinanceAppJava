package com.example.FinanceApp.mediator;

public class ApplicationImpl extends Application {
    public ApplicationImpl(NotificationMediatorInterface mediator, String email) {
        super(mediator, email);
    }

    @Override
    public void notifyUser(String message) {
        System.out.println("Send email notification: " + message + " to: " + email + "\n");
        mediator.sendNotification(this, message);
    }
}
