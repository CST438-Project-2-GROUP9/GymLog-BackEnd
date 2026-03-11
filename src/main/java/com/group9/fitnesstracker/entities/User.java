package com.group9.fitnesstracker.entities;

import jakarta.persistence.*;

/**
 * User Table
 *
 * @author: Dima Krayilo
 * @since: 2/25/2026
 * @version: 0.2.0
 *
 */

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    public User() {
    }

    public User(String username, Boolean isAdmin) {
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
