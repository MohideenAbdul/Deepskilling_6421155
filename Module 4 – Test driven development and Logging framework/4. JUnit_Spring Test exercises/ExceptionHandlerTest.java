package com.example;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.UserController;
import com.example.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserController.class)
public class ExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
    void testUserNotFoundHandled() throws Exception {
        when(userService.getUserById(999L)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/users/999"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("User not found"));
    }
}
