package com.example.FinanceApp.service;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.service.base.UserServiceInterface;
import org.springframework.stereotype.Service;
import com.example.FinanceApp.entity.base.User;
import java.util.ArrayList;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        // Przykładowi użytkownicy
        users.add(new User("Jan Kowalski", "jan.kowalski@example.com"));
        users.add(new User("Anna Nowak", "anna.nowak@example.com"));
    }

    // Pobieranie listy użytkowników i mapowanie na DTO
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user.getName(), user.getEmail()));
        }
        return userDTOs;
    }

    // Pobieranie jednego użytkownika i klonowanie DTO
    public UserDTO getUserClone(int index) {
        if (index < 0 || index >= users.size()) {
            return null;
        }
        User user = users.get(index);
        UserDTO originalDTO = new UserDTO(user.getName(), user.getEmail());
        return (UserDTO) originalDTO.clone(); // Klonowanie DTO
    }

}
