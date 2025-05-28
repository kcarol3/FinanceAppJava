package com.example.FinanceApp.service;

import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.user.UserStateService;
import com.example.FinanceApp.state.UserContext;
import com.example.FinanceApp.state.UserStateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserStateServiceTest {

    private UserRepository userRepository;
    private UserStateService userStateService;
    private User user;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userStateService = new UserStateService(userRepository);
        user = new User("Test User", "test@example.com", UserStateType.ACTIVE);
        user.setId(1L);
    }

    @Test
    void testSuspendUserChangesStateAndSaves() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userStateService.suspendUser(1L);

        verify(userRepository).save(user);
        assertEquals(UserStateType.SUSPENDED, user.getState());
    }

    @Test
    void testActivateUserChangesStateAndSaves() {
        user.setState(UserStateType.SUSPENDED);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userStateService.activateUser(1L);

        verify(userRepository).save(user);
        assertEquals(UserStateType.ACTIVE, user.getState());
    }

    @Test
    void testCloseUserChangesStateAndSaves() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userStateService.closeUser(1L);

        verify(userRepository).save(user);
        assertEquals(UserStateType.CLOSED, user.getState());
    }

    @Test
    void testSuspendUserThrowsWhenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userStateService.suspendUser(99L));
    }

    @Test
    void testActivateUserThrowsWhenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userStateService.activateUser(99L));
    }

    @Test
    void testCloseUserThrowsWhenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> userStateService.closeUser(99L));
    }
}
