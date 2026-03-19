package com.group9.fitnesstracker;

import com.group9.fitnesstracker.controllers.AdminController;
import com.group9.fitnesstracker.entities.User;
import com.group9.fitnesstracker.repository.UserRepository;
import com.group9.fitnesstracker.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AdminControllerTest {
    @InjectMocks
    private AdminController adminController;

    @Mock
    private UserService userService;

    private User user_test_one;
    private User user_test_two;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user_test_one = new User("TestOne", false);
        user_test_two = new User("TestTwo", false);
    }

    @Test
    void getAllUsers() {
        // Create fake admin user
        User adminUser = new User("admin@csumb.edu", true);

        // SecurityContext setup with authenticated user
        Authentication auth = new UsernamePasswordAuthenticationToken(
                adminUser.getUsername(),
                null,
                List.of(() -> "ROLE_ADMIN") // ensures isAuthenticated() is true
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        // mock userService to return the admin user
        Mockito.when(userService.getUserByUsername(adminUser.getUsername()))
                .thenReturn(Optional.of(adminUser));


        // Creating fake list
        List<User> users = Arrays.asList(user_test_one, user_test_two);

        // Mock the behavior of userRepository:
        // Whenever getAllUsers() is called on userRepository,
        // just return our fake users list instead of hitting the real database
        Mockito.when(userService.getAllUsers()).thenReturn(users);


        // Step 4: call controller
        ResponseEntity<List<User>> result = adminController.getUsers();

        // Step 5: assert
        List<User> user_res = result.getBody();
        assertEquals(2, user_res.size());
    }


}
