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
    User getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    boolean usernameExists(String username);

    boolean usernameExits(String username);

    boolean deleteUserById(long id);

    boolean updateUserPrivelege(long id, boolean status);
    boolean deleteWorkoutById(long id);
    void saveUser(String username,boolean user);
}
