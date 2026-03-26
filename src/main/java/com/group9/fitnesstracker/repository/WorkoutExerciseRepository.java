package com.group9.fitnesstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fitnesstracker.entities.WorkoutExercise;

import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findByWorkoutId(Long workoutId);
}

