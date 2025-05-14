package com.example.FinanceApp.service.base.UserService;

//Tydzień 7, ISP 6
public interface UserStateServiceInterface {
    void activateUser(Long userId);

    void suspendUser(Long userId);

    void closeUser(Long userId);
}
//Tydzień 7, ISP 6, koniec