package com.user.repository;

import com.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_shouldReturnUser() {
        User user = new User();
        user.setUsername("Alice");
        user.setEmail("alice@test.com");
        user.setPassword("password");
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("alice@test.com");

        assertTrue(foundUser.isPresent());
    }
}

