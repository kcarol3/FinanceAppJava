package com.example.FinanceApp.state;

public class SuspendedState implements UserState {
    public void suspend(UserContext context) {
        System.out.println("Użytkownik już jest zawieszony.");
    }

    public void activate(UserContext context) {
        System.out.println("Użytkownik został przywrócony.");
        context.setState(new ActiveState());
        context.getUser().setState(UserStateType.ACTIVE);
    }

    public void close(UserContext context) {
        System.out.println("Użytkownik został usunięty.");
        context.setState(new ClosedState());
        context.getUser().setState(UserStateType.CLOSED);
    }

    public String getName() {
        return "SUSPENDED";
    }
}
