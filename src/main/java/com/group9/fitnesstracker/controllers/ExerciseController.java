package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.entities.Exercise;
import com.group9.fitnesstracker.services.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Exercise Controller
 *
 * @author: Dima Krayilo
 * @since: 3/18/2026
 * @version: 0.1.0
 *
 */

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        return new ResponseEntity<>(exerciseService.getAllExercises(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExerciseById(@PathVariable Long id) {
        return exerciseService.getExerciseById(id)
                .map(exercise -> new ResponseEntity<>(exercise, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        return new ResponseEntity<>(exerciseService.saveExercise(exercise), HttpStatus.CREATED);
    }
}
