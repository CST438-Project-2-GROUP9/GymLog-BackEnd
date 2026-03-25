package com.group9.fitnesstracker.repository;

/**
 * User Repository
 *
 * @author: Dima Krayilo
 * @since: 2/25/2026
 * @version: 0.1.0
 *
 */
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.group9.fitnesstracker.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface  UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM \"user\" WHERE username = :username", nativeQuery = true)
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM \"user\"", nativeQuery = true)
    List<User> getAllUsers();

    @Query(value = "SELECT * FROM \"user\" WHERE user_id = :id", nativeQuery = true)
    User getUserId(long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM \"user\" WHERE user_id = :id", nativeQuery = true)
    int deleteUserById(long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE \"user\" SET is_admin = :status WHERE user_id = :id", nativeQuery = true)
    int updateUserPrivelege(long id, boolean status);

    @Modifying
    @Transactional
    @Query(value ="INSERT INTO \"user\" (username, is_admin) VALUES (:email, :status)", nativeQuery = true)
    void saveUser(String email, boolean status);
}


