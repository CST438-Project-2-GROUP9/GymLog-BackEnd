package com.group9.fitnesstracker;

import com.group9.fitnesstracker.controllers.AdminController;
import com.group9.fitnesstracker.entities.User;
import com.group9.fitnesstracker.repository.UserRepository;
import com.group9.fitnesstracker.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
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

        // call controller's method getUsers() for getting all users
        ResponseEntity<List<User>> result = adminController.getUsers();

        // assert
        List<User> user_res = result.getBody();
        assertEquals(2, user_res.size());

        // verify getAllUsers method was called
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void getSingleUser() {
        User adminUser = new User("admin@csumb.edu", true);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                adminUser.getUsername(),
                null,
                List.of(() -> "ROLE_ADMIN")
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        Mockito.when(userService.getUserByUsername(adminUser.getUsername()))
                .thenReturn(Optional.of(adminUser));

        // Mock the behavior of userRepository:
        // Whenever getUserById() is called on userRepository,
        // just return our fake user (a single user) instead of hitting the real database
        Mockito.when(userService.getUserById(1L)).thenReturn(user_test_one);

        // call controller's method getUserById
        ResponseEntity<User> result_one = adminController.getUserById(1L);

        // For the other user (user # 2)
        Mockito.when(userService.getUserById(2L)).thenReturn(user_test_two);
        ResponseEntity<User> result_two = adminController.getUserById(2L);

        // assert
        User user_one_res = result_one.getBody();
        User user_two_res = result_two.getBody();
        assertEquals("TestOne", user_one_res.getUsername());
        assertEquals("TestTwo", user_two_res.getUsername());

        // verify
        verify(userService, times(1)).getUserById(1L);
        verify(userService, times(1)).getUserById(2L);
    }

    @Test
    void deleteUser() {
        User adminUser = new User("admin@csumb.edu", true);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                adminUser.getUsername(),
                null,
                List.of(() -> "ROLE_ADMIN") // ensures isAuthenticated() is true
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        Mockito.when(userService.getUserByUsername(adminUser.getUsername()))
                .thenReturn(Optional.of(adminUser));

        // Mock the behavior of userRepository:
        // Whenever deleteuserById is called on userRepository,
        // The first thenReturn is true meaning user was successfully deleted
        // and thenReturn when calling it again should be false, PS: 1L means Long 1
        Mockito.when(userService.deleteUserById(1L)).thenReturn(true).thenReturn(false);

        // Deletes user
        ResponseEntity<Void> result = adminController.deleteUser(1L);

        // assert, check http code ok for success
        assertEquals(HttpStatus.OK, result.getStatusCode());

        // assert, tries to delete same user (should be no user found)
        ResponseEntity<Void> result_two = adminController.deleteUser(1L);
        assertEquals(HttpStatus.NOT_FOUND, result_two.getStatusCode());

        // assert
        verify(userService, times(2)).deleteUserById(1L);

    }

    @Test
    void updateUser() {
        User adminUser = new User("admin@csumb.edu", true);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                adminUser.getUsername(),
                null,
                List.of(() -> "ROLE_ADMIN") // ensures isAuthenticated() is true
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        Mockito.when(userService.getUserByUsername(adminUser.getUsername()))
                .thenReturn(Optional.of(adminUser));


        // Mock the behavior of userRepository:
        // Whenever updateUserPrivilege is called on userRepository,
        // The thenReturn returns true, meaning user privilege set to true (isAdmin)
        Mockito.when(userService.updateUserPrivelege(1L,true)).thenReturn(true);
        ResponseEntity<User> result = adminController.updateUser(1L, true);

        // assert and verify
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(userService, times(1)).updateUserPrivelege(1L,true);

    }
}
