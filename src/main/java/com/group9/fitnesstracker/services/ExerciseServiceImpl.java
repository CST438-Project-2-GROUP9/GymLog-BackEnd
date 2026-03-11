package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.entities.Exercise;
import com.group9.fitnesstracker.repository.ExerciseRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Exercise Service Implementation
 *
 * @author: Dima Krayilo
 * @since: 3/10/2026
 * @version: 0.1.0
 *
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> getAllExercises() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<Exercise> getExerciseById(Long id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public Exercise saveExercise(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
