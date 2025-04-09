package com.example.FinanceApp.state;

//Tydzien 5, State 1, dodanie stanu konta
public interface UserState {
    void suspend(UserContext context);
    void activate(UserContext context);
    void close(UserContext context);
    String getName();
}
//Koniec, Tydzien 5, Wzorzec State 1