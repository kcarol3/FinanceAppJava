package com.example.FinanceApp.controller;

import com.example.FinanceApp.mediator.Application;
import com.example.FinanceApp.mediator.ApplicationImpl;
import com.example.FinanceApp.mediator.NotificationMediatorInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private NotificationMediatorInterface notificationMediator;

    public NotificationController(NotificationMediatorInterface notificationMediator) {
        this.notificationMediator = notificationMediator;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestParam String email, @RequestParam String message) {
        Application emailApp = new ApplicationImpl(notificationMediator, email);
        notificationMediator.add(emailApp);
        emailApp.notifyUser(message);
        return ResponseEntity.ok("Powiadomienie e-mailowe wysłane!");
    }
}
