package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.entities.User;
import com.group9.fitnesstracker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        // System.out.println("Logged in username: " + authentication.getName());
        Optional<User> user = userService.getUserByUsername(username);
        return user.isPresent() && user.get().getIsAdmin();
    }

    /**
     * Gets all users (Admin only)
     * @return all the users in the database, or an error
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        if (isUserAdmin()) {

            List<User> users = userService.getAllUsers();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(users, HttpStatus.OK);
            }

        }
        // If the current user is not an admin, return HTTP 403 Forbidden
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * Gets single user (Admin only)
     * @Param user_id
     * @return a single User in the database based on the given user_id, or an error
     */
    @GetMapping("/users/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable long user_id) {
        if (isUserAdmin()) {

            User user = this.userService.getUserById(user_id);

            if (user != null) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        // If the current user is not an admin, return HTTP 403 Forbidden
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * Deletes a User (Admin only)
     * Deletes a user based on the given user_id in the database
     * @Param user_id
     * @return Success message or Fail message
     */
    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long user_id) {
        if (isUserAdmin()) {
            boolean isDeleted = this.userService.deleteUserById(user_id);

            if (isDeleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        // If the current user is not an admin, return HTTP 403 Forbidden with a message
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PatchMapping("/users/{user_id}")
    public ResponseEntity<Void> updateUser(@PathVariable long user_id, boolean status) {
        if (isUserAdmin()) {
            boolean isUpdated = this.userService.updateUserPrivelege(user_id, status);

            if (isUpdated) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


}
