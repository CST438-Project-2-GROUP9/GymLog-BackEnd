package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/getUsers")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }


}
