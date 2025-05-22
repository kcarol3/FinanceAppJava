package com.example.FinanceApp.service.user;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.base.UserService.UserManagementServiceInterface;
import com.example.FinanceApp.service.base.UserService.UserMementoServiceInterface;
import com.example.FinanceApp.state.UserStateType;
import org.springframework.stereotype.Service;
import com.example.FinanceApp.entity.base.User;

import java.util.ArrayList;

import java.util.List;

@Service
public class UserService implements UserManagementServiceInterface {

    private final List<User> users = new ArrayList<>();
    private final UserRepository userRepository;
    private final UserMementoServiceInterface userMementoService;

    public UserService(UserRepository userRepository, UserMementoServiceInterface userMementoService) {
        this.userRepository = userRepository;
        this.userMementoService = userMementoService;
        initializeUsers();
    }

    private void initializeUsers() {
        User user1 = createUser("Anna Nowak", "anna.nowak@example.com");
        User user2 = createUser("Piotr Nowak", "piotr.nowak@example.com");
        users.add(user1);
        users.add(user2);
    }

    private User createUser(String name, String email) {
        return User.builder()
                .setName(name)
                .setEmail(email)
                .setState(UserStateType.ACTIVE)
                .build();
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(new UserDTO(user.getName(), user.getEmail(), user.getState()));
        }
        return userDTOs;
    }

    public UserDTO getUserClone(Long index) throws RuntimeException {
        if (index < 0 || index >= users.size()) {
            return null;
        }
        User user = users.get(Math.toIntExact(index));
        UserDTO originalDTO = new UserDTO(user.getName(), user.getEmail(), user.getState());
        return (UserDTO) originalDTO.clone();
    }

    public User save(UserDTO user) {
        return userRepository.save(new User(user.getName(), user.getEmail(), user.getState()));
    }

    @Override
    public void editUser(Long userId, UserDTO updatedUser) {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            userMementoService.createAndSaveUserMemento(user);
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }
}
