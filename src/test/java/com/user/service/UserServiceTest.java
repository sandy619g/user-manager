package com.user.service;

import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("Alice");
        user1.setEmail("alice@test.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("Bob");
        user2.setEmail("bob@test.com");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_shouldReturnUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Alice");
        user.setEmail("alice@test.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(1L);

        assertTrue(result.isPresent());
        assertEquals("Alice", result.get().getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_shouldThrowUserNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(1L);

        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void createUser_shouldSaveAndReturnUser() {
        User user = new User();
        user.setUsername("Alice");
        user.setEmail("alice@test.com");
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.createUser(user);

        assertEquals("Alice", savedUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUser_shouldUpdateAndReturnUser() {
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setUsername("Alice");
        existingUser.setEmail("alice@test.com");

        User updatedUser = new User();
        updatedUser.setUsername("Alice Updated");
        updatedUser.setEmail("alice.updated@test.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        User result = userService.updateUser(1L, updatedUser);

        assertEquals("Alice Updated", result.getUsername());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void updateUser_shouldThrowUserNotFoundException() {
        User updatedUser = new User();
        updatedUser.setUsername("Alice Updated");
        updatedUser.setEmail("alice.updated@test.com");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(1L, updatedUser));
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).save(any());
    }

    @Test
    void deleteUser_shouldDeleteUserById() {
        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_shouldThrowUserNotFoundException() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).deleteById(anyLong());
    }
}
