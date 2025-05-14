package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.memento.UserMemento;

import java.util.List;

//Tydzień 7, ISP 6 ORYGINAŁ
public interface UserServiceInterface {
    List<UserDTO> getAllUsers();

    UserDTO getUserClone(Long id);

    User save(UserDTO user);

    void editUser(Long userId, UserDTO updatedUser);

    void createAndSaveUserMemento(User user);

    void restoreUserState(Long userId, Long mementoId);

    UserMemento findFirstByUserIdOrderByIdDesc(Long userId);

    void activateUser(Long userId);

    void suspendUser(Long userId);

    void closeUser(Long userId);

    User getUserById(Long id);
}
