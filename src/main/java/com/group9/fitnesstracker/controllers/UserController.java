package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.entities.User;
import com.group9.fitnesstracker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;

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
            response.put("id", null);
            response.put("username", null);
            return response;
        }

        String username = authentication.getName();

        if (authentication.getPrincipal() instanceof org.springframework.security.oauth2.core.user.OAuth2User oauthUser) {
            Object emailAttr = oauthUser.getAttributes().get("email");
            if (emailAttr != null) {
                username = emailAttr.toString();
            }
        }

        Optional<User> userOpt = userService.getUserByUsername(username);

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        response.put("isAdmin", isAdmin);

        if (userOpt.isPresent()) {
            response.put("id", userOpt.get().getId());
            response.put("username", userOpt.get().getUsername());
        } else {
            response.put("id", null);
            response.put("username", username);
        }

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
