package com.example.FinanceApp.state;

public class ClosedState implements UserState {
    public void suspend(UserContext context) {
        System.out.println("Nie można zawiesić usuniętego użytkownika.");
    }

    public void activate(UserContext context) {
        System.out.println("Nie można aktywować usuniętego użytkownika.");
    }

    public void close(UserContext context) {
        System.out.println("Użytkownik już usunięty.");
    }

    public String getName() {
        return "CLOSED";
    }
}
