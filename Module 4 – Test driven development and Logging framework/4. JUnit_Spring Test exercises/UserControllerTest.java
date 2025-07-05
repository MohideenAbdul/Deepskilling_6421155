package com.example;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.UserController;
import com.example.entity.User;
import com.example.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Test
void testGetUser() throws Exception {
    User user = new User();
    user.setId(1L);
    user.setName("Alice");  // ✅ This was missing
    when(userService.getUserById(1L)).thenReturn(user);

    mockMvc.perform(get("/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Alice"));
}

@Test
void testCreateUser() throws Exception {
    User user = new User();
    user.setId(2L);
    user.setName("Bob");  // ✅ This was missing
    when(userService.saveUser(any())).thenReturn(user);

    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":2,\"name\":\"Bob\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Bob"));
}

}
