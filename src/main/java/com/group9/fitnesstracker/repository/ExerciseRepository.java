package com.group9.fitnesstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fitnesstracker.model.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    
}
