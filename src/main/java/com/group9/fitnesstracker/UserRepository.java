package com.group9.fitnesstracker;

/**
 * User Repository
 *
 * @author: Dima Krayilo
 * @since: 2/25/2026
 * @version: 0.1.0
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import com.group9.fitnesstracker.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
