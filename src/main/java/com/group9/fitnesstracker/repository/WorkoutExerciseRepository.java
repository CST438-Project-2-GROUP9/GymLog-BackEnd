package com.group9.fitnesstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fitnesstracker.model.WorkoutExercise;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    
}
