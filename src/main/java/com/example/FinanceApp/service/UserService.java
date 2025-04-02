package com.example.FinanceApp.service;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.memento.UserMemento;
import com.example.FinanceApp.repository.UserMementoRepository;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.base.UserServiceInterface;
import org.springframework.stereotype.Service;
import com.example.FinanceApp.entity.base.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private final List<User> users = new ArrayList<>();
    private final UserRepository userRepository;
    private final UserMementoRepository userMementoRepository;

    public UserService(UserRepository userRepository, UserMementoRepository userMementoRepository) {
        this.userRepository = userRepository;
        this.userMementoRepository = userMementoRepository;
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

    public UserDTO getUserClone(Long index) {
        if (index < 0 || index >= users.size()) {
            return null;
        }
        User user = users.get(Math.toIntExact(index));
        UserDTO originalDTO = new UserDTO(user.getName(), user.getEmail());
        return (UserDTO) originalDTO.clone();
    }

    public User save(UserDTO user) {
        return userRepository.save(new User(user.getName(), user.getEmail()));
    }

    @Override
    public void editUser(Long userId, UserDTO updatedUser) {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null){
            createAndSaveUserMemento(user);
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public UserMemento createAndSaveUserMemento(User user) {
        UserMemento memento = new UserMemento();
        memento.setName(user.getName());
        memento.setEmail(user.getEmail());
        memento.setUser(user);

        user.addMementos(memento);
        return userMementoRepository.save(memento);
    }

    @Override
    @Transactional
    public void restoreUserState(Long userId, Long mementoId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        UserMemento memento = userMementoRepository.findById(mementoId)
                .orElseThrow(() -> new RuntimeException("Memento not found"));

        user.restoreFromMemento(memento);
        userRepository.save(user);
    }

    @Override
    public UserMemento findFirstByUserIdOrderByIdDesc(Long userId) {
        return userMementoRepository.findTopByUserIdOrderByIdDesc(userId);
    }

}
