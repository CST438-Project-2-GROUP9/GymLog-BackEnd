package com.group9.fitnesstracker.controllers;

import com.group9.fitnesstracker.model.Workout;
import com.group9.fitnesstracker.services.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/workout")
public class WorkoutController {

    WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    // Take the value from the URL path and put it into this variable, basically to the parameter
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkout(@PathVariable Long id) {
        return new ResponseEntity<>(this.workoutService.getWorkoutById(id), HttpStatus.OK);
    }
}
