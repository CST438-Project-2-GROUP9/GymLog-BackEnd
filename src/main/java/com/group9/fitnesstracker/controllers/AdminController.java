package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.entities.User;
import com.group9.fitnesstracker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Helper method to check if isAdmin()
    private boolean isUserAdmin() {
        // Get the currently authenticated user; return false if not logged in
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // If authentication is not false
        // Get the username of the logged in person, and checked if it is in database and check if it's an admin
        String username = authentication.getName();

        System.out.println("Logged in username: " + authentication.getName());
        Optional<User> user = userService.getUserByUsername(username);
        return user.isPresent() && user.get().getIsAdmin();
    }

    /**
     * Gets all users (Admin only)
     * @return
     */
    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        if (isUserAdmin()) {
            return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
        }
        // If the current user is not an admin, return HTTP 403 Forbidden with a message
        return ResponseEntity.status(403).body("Forbidden: Admins only");
    }

    /**
     * Gets single user (Admin only)
     * @return
     */
    @GetMapping("/users/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable long user_id) {
        if (isUserAdmin()) {
            return new ResponseEntity<>(this.userService.getUserById(user_id), HttpStatus.OK);
        }
        // If the current user is not an admin, return HTTP 403 Forbidden with a message
        return ResponseEntity.status(403).body("Forbidden: Admins only");
    }


}
