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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * User Rest Controller
 *
 * @author: Dima Krayilo
 * @since: 3/2/2026
 * @version: 0.1.0
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {


    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets the currently log in user
     * @return <Map<String, Object>> the user logged in
     */
    @GetMapping("/currentUser")
    public Map<String, Object> me(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();

        if (authentication == null || !authentication.isAuthenticated()) {
            response.put("isAdmin", false);
            return response;
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        response.put("isAdmin", isAdmin);
        System.out.println("Authentication: " + authentication);
        System.out.println("Authorities: " + authentication.getAuthorities());
        return response;
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.getUserByUsername(username), HttpStatus.OK);
    }
    @GetMapping("/usernameExists/{username}")
    public ResponseEntity<?> getUsernameExist(@PathVariable String username) {
        boolean exists = this.userService.usernameExists(username);
        return new ResponseEntity<>(java.util.Map.of("username",username,"exists", exists), HttpStatus.OK);
    }


    @GetMapping("/getUserById/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
    }
}
