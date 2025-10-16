package com.grouptwelve.grouptwelveBE.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.grouptwelve.grouptwelveBE.model.User;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindUser() {
        User user = new User("Test User", "test@example.com", "password123");
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser.getUserId());
        assertEquals("Test User", savedUser.getName());
        assertEquals("test@example.com", savedUser.getEmail());
    }

    @Test
    void testFindById() {
        User user = new User("John Doe", "john@example.com", "pass123");
        User savedUser = userRepository.save(user);

        User foundUser = userRepository.findById(savedUser.getUserId()).orElse(null);
        
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
        assertEquals("john@example.com", foundUser.getEmail());
    }

    @Test
    void testFindAll() {
        User user1 = new User("User 1", "user1@example.com", "pass1");
        User user2 = new User("User 2", "user2@example.com", "pass2");
        
        userRepository.save(user1);
        userRepository.save(user2);

        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    void testDeleteUser() {
        User user = new User("Delete Me", "delete@example.com", "password");
        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getUserId());
        
        assertFalse(userRepository.findById(savedUser.getUserId()).isPresent());
    }
}