package com.example.FinanceApp.controller;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Pobieranie wszystkich użytkowników (bez klonowania)
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Pobieranie klonowanego użytkownika na podstawie indeksu
    @GetMapping("/{index}/clone")
    public UserDTO getClonedUser(@PathVariable int index) {
        return userService.getUserClone(index);
    }
}
