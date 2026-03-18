package com.group9.fitnesstracker.services;

import com.group9.fitnesstracker.dto.AddExerciseToWorkoutRequest;
import com.group9.fitnesstracker.entities.Exercise;
import com.group9.fitnesstracker.entities.Workout;
import com.group9.fitnesstracker.entities.WorkoutExercise;
import com.group9.fitnesstracker.repository.ExerciseRepository;
import com.group9.fitnesstracker.repository.WorkoutExerciseRepository;
import com.group9.fitnesstracker.repository.WorkoutRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutEntryService {
    private final WorkoutRepository workoutRepo;
    private final ExerciseRepository exerciseRepo;
    private final WorkoutExerciseRepository workoutExerciseRepo;

    public WorkoutEntryService(WorkoutRepository workoutRepo, ExerciseRepository exerciseRepo, WorkoutExerciseRepository workoutExerciseRepo) {
        this.workoutRepo = workoutRepo;
        this.exerciseRepo = exerciseRepo;
        this.workoutExerciseRepo = workoutExerciseRepo;
    }
    @Transactional
    public Long addExercise(Long ownerId, Long workoutId, AddExerciseToWorkoutRequest req) {
        // Implementation for adding exercise to workout
        Workout workout = workoutRepo.findByIdAndUserId(workoutId, ownerId)
            .orElseThrow(() -> new RuntimeException("Workout not found"));
        Exercise exercise = exerciseRepo.findById(req.getExerciseId())
            .orElseThrow(() -> new RuntimeException("Exercise not found"));

        WorkoutExercise entry = new WorkoutExercise(workoutId, exercise.getId(), req.getSets(), req.getReps());
        workoutExerciseRepo.save(entry);
        return entry.getId();
    }

    @Transactional
    public Long createRandomWorkout(Long userId, String bodyRegion) {
        Workout workout = new Workout(userId, "Random " + bodyRegion + " Workout");
        workout = workoutRepo.save(workout);

        List<Exercise> randomExercises = exerciseRepo.findRandomExercisesByBodyRegion(bodyRegion, 3);

        if (randomExercises.isEmpty()) {
            throw new RuntimeException("No exercises found for region: " + bodyRegion);
        }

        for (Exercise ex : randomExercises) {
            WorkoutExercise entry = new WorkoutExercise(workout.getId(), ex.getId(), 3, 10);
            workoutExerciseRepo.save(entry);
        }

        return workout.getId();
    }
}
