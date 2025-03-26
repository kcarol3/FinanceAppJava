package com.example.FinanceApp.controller;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.service.UserService;
import com.example.FinanceApp.service.base.NewUserFacadeInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final NewUserFacadeInterface newUserFacade;

    public UserController(UserService userService, NewUserFacadeInterface newUserFacade) {
        this.userService = userService;
        this.newUserFacade = newUserFacade;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{index}/clone")
    public UserDTO getClonedUser(@PathVariable int index) {
        return userService.getUserClone(index);
    }

    @PostMapping("/new")
    public void saveNewUser(@RequestBody UserDTO userDTO) {
        newUserFacade.saveNewUser(userDTO);
    }
}
