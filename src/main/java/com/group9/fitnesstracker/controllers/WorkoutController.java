package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.entities.Workout;
import com.group9.fitnesstracker.services.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Workout Controller
 *
 * @author: Dima Krayilo
 * @since: 3/18/2026
 * @version: 0.1.0
 *
 */

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<List<Workout>> getAllWorkouts() {
        return new ResponseEntity<>(workoutService.getAllWorkouts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkoutById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id)
                .map(workout -> new ResponseEntity<>(workout, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Workout> createWorkout(@RequestBody Workout workout) {
        return new ResponseEntity<>(workoutService.createWorkout(workout), HttpStatus.CREATED);
    }
}
