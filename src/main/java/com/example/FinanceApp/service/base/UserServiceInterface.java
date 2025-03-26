package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.entity.base.User;

import java.util.List;

public interface UserServiceInterface {
    List<UserDTO> getAllUsers();
    UserDTO getUserClone(int id);

    User save(UserDTO user);
}
