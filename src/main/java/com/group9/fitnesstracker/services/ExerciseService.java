package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.entities.Exercise;

import java.util.List;
import java.util.Optional;

/**
 * Exercise Service Interface
 *
 * @author: Dima Krayilo
 * @since: 3/10/2026
 * @version: 0.1.0
 *
 */
public interface ExerciseService {
    List<Exercise> getAllExercises();
    Optional<Exercise> getExerciseById(Long id);
    Exercise saveExercise(Exercise exercise);
}
