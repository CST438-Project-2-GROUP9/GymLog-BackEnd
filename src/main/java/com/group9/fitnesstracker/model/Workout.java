package com.group9.fitnesstracker.model;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "workouts")
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    //replace 
    @Column(nullable = false)
    private String ownerId;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    public Workout() {
    }
    public Workout(String ownerId, String name) {
        this.ownerId = ownerId;
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
