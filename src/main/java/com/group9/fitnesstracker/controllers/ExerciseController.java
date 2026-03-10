package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.model.Exercise;
import com.group9.fitnesstracker.services.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExercise(@PathVariable Long id) {
        return new ResponseEntity<>(this.exerciseService.getExerciseById(id), HttpStatus.OK);
    }
}
