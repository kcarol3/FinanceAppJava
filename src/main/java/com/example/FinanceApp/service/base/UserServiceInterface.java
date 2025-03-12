package com.example.FinanceApp.service.base;

import com.example.FinanceApp.dto.UserDTO;

import java.util.List;

public interface UserServiceInterface {
    List<UserDTO> getAllUsers();
    UserDTO getUserClone(int id);
}
