package com.grouptwelve.grouptwelveBE.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserCreation() {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testUserConstructor() {
        User user = new User("Jane Smith", "jane.smith@example.com", "securepass");
        
        assertEquals("Jane Smith", user.getName());
        assertEquals("jane.smith@example.com", user.getEmail());
        assertEquals("securepass", user.getPassword());
    }

    @Test
    void testUserIdGeneration() {
        User user = new User();
        // ID should be null before persistence
        assertNull(user.getUserId());
    }
}