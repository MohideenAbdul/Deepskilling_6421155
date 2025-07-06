
package com.example.mocktest.service;

import com.example.mocktest.model.User;
import com.example.mocktest.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    public void testGetUserById() {
        UserRepository mockRepo = mock(UserRepository.class);
        User user = new User(1L, "John Doe");
        when(mockRepo.findById(1L)).thenReturn(Optional.of(user));

        UserService userService = new UserService(mockRepo);
        User result = userService.getUserById(1L);

        assertEquals("John Doe", result.getName());
    }
}
