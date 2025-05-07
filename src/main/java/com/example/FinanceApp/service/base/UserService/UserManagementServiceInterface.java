package com.example.FinanceApp.service.base.UserService;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.entity.base.User;

import java.util.List;

//Tydzień 7, ISP 6
public interface UserManagementServiceInterface {
    List<UserDTO> getAllUsers();
    UserDTO getUserClone(Long id);
    User save(UserDTO user);
    void editUser(Long userId, UserDTO updatedUser);
    User getUserById(Long id);
}
//Tydzień 7, ISP 6, koniec
