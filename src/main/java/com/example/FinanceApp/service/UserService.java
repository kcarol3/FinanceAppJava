package com.example.FinanceApp.service;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.base.UserServiceInterface;
import org.springframework.stereotype.Service;
import com.example.FinanceApp.entity.base.User;
import java.util.ArrayList;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private final List<User> users = new ArrayList<>();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        User user1 = User.builder()
                .setName("Anna Nowak")
                .setEmail("anna.nowak@example.com")
                .build();

        User user2 = User.builder()
                .setName("Piotr Nowak")
                .setEmail("piotr.nowak@example.com")
                .build();

        users.add(user1);
        users.add(user2);
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user.getName(), user.getEmail()));
        }
        return userDTOs;
    }

    public UserDTO getUserClone(int index) {
        if (index < 0 || index >= users.size()) {
            return null;
        }
        User user = users.get(index);
        UserDTO originalDTO = new UserDTO(user.getName(), user.getEmail());
        return (UserDTO) originalDTO.clone();
    }

    public User save(UserDTO user) {
        return userRepository.save(new User(user.getName(), user.getEmail()));
    }
}
