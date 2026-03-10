package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.model.Workout;
import com.group9.fitnesstracker.repository.WorkoutRepository;
import com.group9.fitnesstracker.repository.ExerciseRepository;
import com.group9.fitnesstracker.repository.WorkoutExerciseRepository;
import com.group9.fitnesstracker.services.WorkoutExerciseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/workout_exercises")
public class WorkoutExercisesController {

    WorkoutExerciseService workoutExerciseService;
    WorkoutRepository workoutRepo;
    ExerciseRepository exerciseRepo;
    WorkoutExerciseRepository workoutExerciseRepo;

    public WorkoutExercisesController(WorkoutExerciseService workoutExerciseService, WorkoutRepository workoutRepo) {
        this.workoutExerciseService = workoutExerciseService;
        this.workoutRepo = workoutRepo;
    }

    @GetMapping("/{workoutId}/exercises")
    public ResponseEntity<?> getExercises(@PathVariable Long workoutId) {

        Workout workout = workoutRepo.findById(workoutId)
                .orElseThrow(() -> new RuntimeException("Workout not found"));


        List<Map<String, Object>> exercises = workoutExerciseService.getExercisesByWorkoutId(workoutId);

        // Use LinkedHashMap to preserve order: first workoutName, then exercises
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("workoutName", workout.getName());
        response.put("exercises", exercises);

        return ResponseEntity.ok(response);
    }
}