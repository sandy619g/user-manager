package com.user.controller;

import com.user.model.User;
import com.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllUsers_shouldReturnAllUsers() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("Alice");
        user1.setEmail("alice@test.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("Bob");
        user2.setEmail("bob@test.com");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        var users = userController.getAllUsers();

        assertEquals(2, users.size());
        assertEquals("Alice", users.get(0).getUsername());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getUserById_shouldReturnUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Alice");
        user.setEmail("alice@test.com");

        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        var response = userController.getUserById(1L);

        assertEquals(ResponseEntity.ok(user), response);
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void getUserById_shouldReturnNotFound() {
        when(userService.getUserById(1L)).thenReturn(Optional.empty());

        var response = userController.getUserById(1L);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void createUser_shouldCreateUser() {
        User user = new User();
        user.setUsername("Alice");
        user.setEmail("alice@test.com");

        when(userService.createUser(user)).thenReturn(user);

        var createdUser = userController.createUser(user);

        assertEquals("Alice", createdUser.getBody().getUsername());
        verify(userService, times(1)).createUser(user);
    }

    @Test
    void updateUser_shouldUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Alice Updated");
        user.setEmail("alice@test.com");

        when(userService.updateUser(1L, user)).thenReturn(user);

        var response = userController.updateUser(1L, user);

        assertEquals(ResponseEntity.ok(user), response);
        verify(userService, times(1)).updateUser(1L, user);
    }

    @Test
    void deleteUser_shouldDeleteUser() {
        var response = userController.deleteUser(1L);

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(userService, times(1)).deleteUser(1L);
    }
}

