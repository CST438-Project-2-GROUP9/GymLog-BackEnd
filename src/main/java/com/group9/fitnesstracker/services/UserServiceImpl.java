package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.UserRepository;
import com.group9.fitnesstracker.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService Implementation
 *
 * @author: Dima Krayilo
 * @since: 3/3/2026
 * @version: 0.1.0
 *
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
