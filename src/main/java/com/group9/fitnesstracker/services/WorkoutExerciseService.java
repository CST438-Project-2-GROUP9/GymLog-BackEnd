package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.model.WorkoutExercise;
import java.util.List;
import java.util.Map;

public interface WorkoutExerciseService {
    List<Map<String, Object>> getExercisesByWorkoutId(Long workoutId);
}
