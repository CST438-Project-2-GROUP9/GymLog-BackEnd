package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.repository.UserRepository;
import com.group9.fitnesstracker.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public Optional<User> getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean usernameExists(String username) {
        return false;
    }

    @Override
    public boolean usernameExits(String username){
        return userRepository.existsByUsername(username);
    }
    
}
