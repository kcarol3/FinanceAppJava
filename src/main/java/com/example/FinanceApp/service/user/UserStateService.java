package com.example.FinanceApp.service.user;

import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.base.UserService.UserStateServiceInterface;
import com.example.FinanceApp.state.UserContext;
import org.springframework.stereotype.Service;

//tydzien 7, IS 3, uzycie
@Service
public class UserStateService implements UserStateServiceInterface {
    private final UserRepository userRepository;

    public UserStateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void suspendUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        UserContext context = new UserContext(user);
        context.suspend();
        userRepository.save(user);
    }

    @Override
    public void activateUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        UserContext context = new UserContext(user);
        context.activate();
        userRepository.save(user);
    }

    @Override
    public void closeUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        UserContext context = new UserContext(user);
        context.close();
        userRepository.save(user);
    }
}
