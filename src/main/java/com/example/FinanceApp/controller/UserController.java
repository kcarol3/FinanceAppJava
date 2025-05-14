package com.example.FinanceApp.controller;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.service.base.NewUserFacadeInterface;
import com.example.FinanceApp.service.base.UserService.UserManagementServiceInterface;
import com.example.FinanceApp.service.base.UserService.UserMementoServiceInterface;
import com.example.FinanceApp.service.base.UserService.UserStateServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserManagementServiceInterface userService;
    private final UserMementoServiceInterface userMementoService;
    private final UserStateServiceInterface userStateService;
    private final NewUserFacadeInterface newUserFacade;

    public UserController(UserManagementServiceInterface userService, UserMementoServiceInterface userMementoService, UserStateServiceInterface userStateService, NewUserFacadeInterface newUserFacade) {
        this.userService = userService;
        this.userMementoService = userMementoService;
        this.userStateService = userStateService;
        this.newUserFacade = newUserFacade;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    //Tydzień 8, obsługa wyjątku 5
    @GetMapping("/{index}/clone")
    public ResponseEntity<UserDTO> getClonedUser(@PathVariable Long index) {
        try {
            UserDTO clonedUser = userService.getUserClone(index);
            return ResponseEntity.ok(clonedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    //Koniec,tydzień 8, obsługa wyjątku 5

    @PostMapping("/new")
    public void saveNewUser(@RequestBody UserDTO userDTO) {
        newUserFacade.saveNewUser(userDTO);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userService.editUser(userId, userDTO);
        return new ResponseEntity<>("User has been updated", HttpStatus.OK);
    }

    @PostMapping("/restore/{userId}")
    public String restoreAccountState(@PathVariable Long userId) {
        try {
            userMementoService.restoreUserState(userId, userMementoService.findFirstByUserIdOrderByIdDesc(userId).getId());
            return "User state with ID " + userId + " has been restored to the previous state.";
        } catch (Exception e) {
            return "Error restoring user state: " + e.getMessage();
        }
    }

    @PostMapping("/{id}/suspend")
    public ResponseEntity<Void> suspendUser(@PathVariable Long id) {
        userStateService.suspendUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<Void> activateUser(@PathVariable Long id) {
        userStateService.activateUser(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Void> closeUser(@PathVariable Long id) {
        userStateService.closeUser(id);
        return ResponseEntity.ok().build();
    }
}
