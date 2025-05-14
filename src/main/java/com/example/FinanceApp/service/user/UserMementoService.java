package com.example.FinanceApp.service.user;

import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.memento.UserMemento;
import com.example.FinanceApp.repository.UserMementoRepository;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.base.UserService.UserMementoServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserMementoService implements UserMementoServiceInterface {
    private final UserMementoRepository userMementoRepository;
    private final UserRepository userRepository;

    public UserMementoService(UserMementoRepository userMementoRepository, UserRepository userRepository) {
        this.userMementoRepository = userMementoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createAndSaveUserMemento(User user) {
        UserMemento memento = new UserMemento();
        memento.setName(user.getName());
        memento.setEmail(user.getEmail());
        memento.setUser(user);

        user.addMementos(memento);
        userMementoRepository.save(memento);
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
