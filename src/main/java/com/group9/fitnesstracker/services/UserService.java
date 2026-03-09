package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.entities.User;

import java.util.List;
import java.util.Optional;

/**
 * User Service Interface
 *
 * @author: Dima Krayilo
 * @since: 3/2/2026
 * @version: 0.1.0
 *
 */
public interface UserService {
    List<User> getAllUsers();
}
