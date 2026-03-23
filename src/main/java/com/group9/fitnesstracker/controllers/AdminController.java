package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.entities.User;
import com.group9.fitnesstracker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Admin REST controller
 * Includes GET all users, GET single user, DELETE user, UPDATE (Partial) user
 * @author Neil Cabanilla
 * @date 3/22/2026
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets all users (Admin only)
     * @return all the users in the database, or an error
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

    }

    /**
     * Gets single user (Admin only)
     * @Param user_id
     * @return a single User in the database based on the given user_id, or an error
     */
    @GetMapping("/users/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable long user_id) {

        User user = this.userService.getUserById(user_id);

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Deletes a User (Admin only)
     * Deletes a user based on the given user_id in the database
     * @Param user_id
     * @return Success message or Fail message
     */
    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long user_id) {

        boolean isDeleted = this.userService.deleteUserById(user_id);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Updates a User's privilege by giving them admin access or revoking admin access
     * @param user_id The user to be updated
     * @param status Setting true or false for admin status
     * @return a ResponseEntity<User> a User
     */
    @PatchMapping("/users/{user_id}")
    public ResponseEntity<User> updateUser(@PathVariable long user_id,  @RequestParam boolean status) {
        boolean isUpdated = this.userService.updateUserPrivelege(user_id, status);

        if (isUpdated) {
            return new ResponseEntity<>(userService.getUserById(user_id),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
