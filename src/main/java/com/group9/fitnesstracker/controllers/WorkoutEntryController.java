package com.group9.fitnesstracker.controllers;

import java.net.URI;
import java.util.Optional;

import com.group9.fitnesstracker.entities.User;
import com.group9.fitnesstracker.services.UserService;
import com.group9.fitnesstracker.services.WorkoutEntryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import com.group9.fitnesstracker.dto.AddExerciseToWorkoutRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class WorkoutEntryController {
    private final WorkoutEntryService service;
    private final UserService userService;

    public WorkoutEntryController(WorkoutEntryService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }
    private Long ownerId(){
        return 1L;
    }

    @PostMapping("/workouts/{workoutId}/exercises")
    public ResponseEntity<?> addExcerciseToWorkout(@PathVariable Long workoutId,
                                                   @Valid @RequestBody AddExerciseToWorkoutRequest req,
                                                    @AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(java.util.Map.of("error", "User not logged in"));
        }

        String email = principal.getAttribute("email");
        Optional<User> user = userService.getUserByUsername(email);

        if (user.isPresent()) {
            try {
                Long entryId = service.addExercise(user.get().getId(), workoutId, req);
                return ResponseEntity.created(URI.create("/api/workouts/" + workoutId + "/exercises/" + entryId)).body(java.util.Map.of("id", entryId));
            } catch (Exception e) {
                return ResponseEntity.status(404).body(java.util.Map.of("error", e.getMessage()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", "User not found in database"));
        }
    }

    @PostMapping("/workouts/random/{bodyRegion}")
    public ResponseEntity<?> createRandomWorkout(@PathVariable String bodyRegion,
                                                 @AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(java.util.Map.of("error", "User not logged in"));
        }

        String email = principal.getAttribute("email");
        Optional<User> user = userService.getUserByUsername(email);

        if (user.isPresent()) {
            try {
                Long workoutId = service.createRandomWorkout(user.get().getId(), bodyRegion);
                return ResponseEntity.created(URI.create("/api/workouts/" + workoutId))
                        .body(java.util.Map.of(
                                "message", "Random workout created successfully",
                                "workoutId", workoutId
                        ));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(java.util.Map.of("error", e.getMessage()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", "User not found in database"));
        }
    }

    @GetMapping("/workouts/{workoutId}/exercises")
    public ResponseEntity<?> getWorkoutExercises(
            @PathVariable Long workoutId,
            @AuthenticationPrincipal OAuth2User principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(java.util.Map.of("error", "User not logged in"));
        }

        String email = principal.getAttribute("email");
        Optional<User> user = userService.getUserByUsername(email);

        if (user.isPresent()) {
            try {
                // We pass both userId and workoutId to the service to ensure ownership/security
                var entries = service.getExercisesForWorkout(user.get().getId(), workoutId);
                return ResponseEntity.ok(entries);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", e.getMessage()));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", "User not found"));
        }
    }
}
