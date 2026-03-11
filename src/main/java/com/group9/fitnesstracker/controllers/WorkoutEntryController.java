package com.group9.fitnesstracker.controllers;

import java.net.URI;

import com.group9.fitnesstracker.services.WorkoutEntryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.group9.fitnesstracker.dto.AddExerciseToWorkoutRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class WorkoutEntryController {
    private final WorkoutEntryService service;

    public WorkoutEntryController(WorkoutEntryService service) {
        this.service = service;
    }
    private String ownerId(){
        return "1";
    }

    @PostMapping("/workouts/{workoutId}/exercises")
    public ResponseEntity<?> addExcerciseToWorkout(@PathVariable Long WorkoutId, @Valid @RequestBody AddExerciseToWorkoutRequest req){
        try{
            Long entryId= service.addExercise(ownerId(), WorkoutId, req);
            return ResponseEntity.created(URI.create("/api/workouts/"+ WorkoutId + "/exercises/" + entryId)).body(java.util.Map.of("id", entryId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(java.util.Map.of("error", e.getMessage()));
        }
    }
}
