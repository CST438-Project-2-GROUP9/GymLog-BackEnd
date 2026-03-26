package com.group9.fitnesstracker.controllers;

import java.net.URI;

import com.group9.fitnesstracker.services.WorkoutEntryService;
import org.springframework.http.HttpStatus;
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
    private Long ownerId(){
        return 1L;
    }

    @PostMapping("/workouts/{workoutId}/exercises")
    public ResponseEntity<?> addExcerciseToWorkout(@PathVariable Long workoutId, @Valid @RequestBody AddExerciseToWorkoutRequest req){
        try{
            Long entryId= service.addExercise(ownerId(), workoutId, req);
            return ResponseEntity.created(URI.create("/api/workouts/"+ workoutId + "/exercises/" + entryId)).body(java.util.Map.of("id", entryId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(java.util.Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/workouts/random/{bodyRegion}")
    public ResponseEntity<?> createRandomWorkout(@PathVariable String bodyRegion) {
        try {
            Long workoutId = service.createRandomWorkout(ownerId(), bodyRegion);
            return ResponseEntity.created(URI.create("/api/workouts/" + workoutId))
                    .body(java.util.Map.of(
                            "message", "Random workout created successfully",
                            "workoutId", workoutId
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(java.util.Map.of("error", e.getMessage()));
        }
    }
}