package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.model.Workout;
import com.group9.fitnesstracker.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Workout getWorkoutById(Long id) {
        return workoutRepository.getByWorkoutId(id);
    }
}
