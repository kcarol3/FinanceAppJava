package com.example.FinanceApp.service.base.UserService;

import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.memento.UserMemento;

//Tydzień 7, ISP 6
public interface UserMementoServiceInterface {
    void createAndSaveUserMemento(User user);
    void restoreUserState(Long userId, Long mementoId);
    UserMemento findFirstByUserIdOrderByIdDesc(Long userId);
}
//Tydzień 7, ISP 6, koniec