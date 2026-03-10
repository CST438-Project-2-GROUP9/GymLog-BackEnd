package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.model.Exercise;
import com.group9.fitnesstracker.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Exercise getExerciseById(Long id) {
        return exerciseRepository.getByExerciseId(id);
    }
}
