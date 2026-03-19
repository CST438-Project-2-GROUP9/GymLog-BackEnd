package com.group9.fitnesstracker;

import com.group9.fitnesstracker.controllers.UserController;
import com.group9.fitnesstracker.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import com.group9.fitnesstracker.entities.User;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerUnitTest {

    @Test
    void getUserById_returnsOk() {
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);


        when(userService.getUserById(1L)).thenReturn(new User("alice", false));
        ResponseEntity<?> res = controller.getUserById(1L);

        assertEquals(200, res.getStatusCode().value());
        verify(userService).getUserById(1L);
    }

    @Test
    void getUserByUsername_returnsOk() {
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);
        when(userService.getUserByUsername("alice"))
                .thenReturn(Optional.of(new User("alice", false)));

        ResponseEntity<?> res = controller.getUserByUsername("alice");

        assertEquals(200, res.getStatusCode().value());
        verify(userService).getUserByUsername("alice");
    }

    @Test
    void usernameExists_returnsMap() {
        UserService userService = mock(UserService.class);
        UserController controller = new UserController(userService);

        when(userService.usernameExists("alice")).thenReturn(true);

        ResponseEntity<?> res = controller.getUsernameExist("alice");

        assertEquals(200, res.getStatusCode().value());
        assertTrue(res.getBody() instanceof Map);

        Map<?, ?> body = (Map<?, ?>) res.getBody();
        assertEquals("alice", body.get("username"));
        assertEquals(true, body.get("exists"));

        verify(userService).usernameExists("alice");
    }
}