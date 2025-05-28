package com.example.FinanceApp.service;

import com.example.FinanceApp.dto.UserDTO;
import com.example.FinanceApp.entity.base.User;
import com.example.FinanceApp.repository.UserRepository;
import com.example.FinanceApp.service.base.UserService.UserMementoServiceInterface;
import com.example.FinanceApp.service.user.UserService;
import com.example.FinanceApp.state.UserStateType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserMementoServiceInterface userMementoService;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userMementoService = mock(UserMementoServiceInterface.class);
        userService = new UserService(userRepository, userMementoService);
    }

    @Test
    void testGetAllUsersReturnsInitializedUsers() {
        List<UserDTO> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertEquals("Anna Nowak", users.get(0).getName());
        assertEquals("Piotr Nowak", users.get(1).getName());
    }

    @Test
    void testGetUserCloneReturnsClonedUser() {
        UserDTO clone = userService.getUserClone(0L);
        assertNotNull(clone);
        assertEquals("Anna Nowak", clone.getName());
        assertNotSame(clone, userService.getAllUsers().get(0));
    }

    @Test
    void testGetUserCloneReturnsNullForInvalidIndex() {
        assertNull(userService.getUserClone(10L));
    }

    @Test
    void testSaveCallsRepositoryWithCorrectUser() {
        UserDTO dto = new UserDTO("Jan Kowalski", "jan@example.com", UserStateType.CLOSED);
        User savedUser = new User(dto.getName(), dto.getEmail(), dto.getState());

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.save(dto);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        assertEquals("Jan Kowalski", captor.getValue().getName());
        assertEquals("jan@example.com", result.getEmail());
    }

    @Test
    void testEditUserUpdatesUserFields() {
        Long userId = 1L;
        User existingUser = new User("Old Name", "old@example.com", UserStateType.ACTIVE);
        existingUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        UserDTO updated = new UserDTO("New Name", "new@example.com", UserStateType.ACTIVE);
        userService.editUser(userId, updated);

        assertEquals("New Name", existingUser.getName());
        assertEquals("new@example.com", existingUser.getEmail());
        verify(userMementoService).createAndSaveUserMemento(existingUser);
    }

    @Test
    void testEditUserThrowsExceptionWhenUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                userService.editUser(99L, new UserDTO("A", "B", UserStateType.ACTIVE)));
    }

    @Test
    void testGetUserByIdCallsRepositoryGetById() {
        Long id = 5L;
        User user = new User("Test", "test@example.com", UserStateType.CLOSED);
        when(userRepository.getById(id)).thenReturn(user);

        User result = userService.getUserById(id);
        assertEquals("Test", result.getName());
        verify(userRepository).getById(id);
    }
}

