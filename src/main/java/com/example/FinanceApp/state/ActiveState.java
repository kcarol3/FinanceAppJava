package com.example.FinanceApp.state;


public class ActiveState implements UserState {
    public void suspend(UserContext context) {
        System.out.println("Użytkownik został zawieszony.");
        context.setState(new SuspendedState());
        context.getUser().setState(UserStateType.SUSPENDED);

    }

    public void activate(UserContext context) {
        System.out.println("Użytkownik już jest aktywny.");
    }

    public void close(UserContext context) {
        System.out.println("Użytkownik został usunięty.");
        context.setState(new ClosedState());
        context.getUser().setState(UserStateType.CLOSED);
    }

    public String getName() {
        return "ACTIVE";
    }
}
