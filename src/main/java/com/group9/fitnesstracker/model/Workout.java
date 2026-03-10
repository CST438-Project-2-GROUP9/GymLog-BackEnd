package com.group9.fitnesstracker.model;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "workout")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_id")
    private Long id;

    @Column(name = "user_id",nullable = false)
    private Long ownerId;

    @Column(name = "name", nullable = false)
    private String name;

    public Workout() {
    }
    public Workout(Long ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
