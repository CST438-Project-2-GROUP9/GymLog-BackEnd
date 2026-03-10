package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.model.WorkoutExercise;
import com.group9.fitnesstracker.repository.WorkoutExerciseRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class WorkoutExerciseServiceImpl implements WorkoutExerciseService {

    private final WorkoutExerciseRepository workoutExerciseRepository;

    public WorkoutExerciseServiceImpl(WorkoutExerciseRepository repo) {
        this.workoutExerciseRepository = repo;
    }

    // get all exercises for a workout
    public List<Map<String, Object>> getExercisesByWorkoutId(Long workoutId) {
        return workoutExerciseRepository.findExercisesWithNamesByWorkoutId(workoutId);
    }
}