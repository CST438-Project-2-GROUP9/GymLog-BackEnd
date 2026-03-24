package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.entities.Workout;

import java.util.List;
import java.util.Optional;

/**
 * Workout Service Interface
 *
 * @author: Dima Krayilo
 * @since: 3/10/2026
 * @version: 0.1.0
 *
 */
public interface WorkoutService {
    List<Workout> getAllWorkouts();
    Optional<Workout> getWorkoutById(Long id);
    Workout createWorkout(Workout workout);

    boolean deleteWorkout(Long id);
}
