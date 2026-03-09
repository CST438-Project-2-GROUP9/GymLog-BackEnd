//package com.group9.fitnesstracker.controller;
//
//import com.group9.fitnesstracker.dto.AddExerciseToWorkoutRequest;
//import com.group9.fitnesstracker.model.Exercise;
//import com.group9.fitnesstracker.model.Workout;
//import com.group9.fitnesstracker.model.WorkoutExercise;
//import com.group9.fitnesstracker.repository.ExerciseRepository;
//import com.group9.fitnesstracker.repository.WorkoutExerciseRepository;
//import com.group9.fitnesstracker.repository.WorkoutRepository;
//
//import jakarta.transaction.Transactional;
//
//public class WorkoutEntryService {
//    private final WorkoutRepository workoutRepo;
//    private final ExerciseRepository exerciseRepo;
//    private final WorkoutExerciseRepository workoutExerciseRepo;
//
//    public WorkoutEntryService(WorkoutRepository workoutRepo, ExerciseRepository exerciseRepo, WorkoutExerciseRepository workoutExerciseRepo) {
//        this.workoutRepo = workoutRepo;
//        this.exerciseRepo = exerciseRepo;
//        this.workoutExerciseRepo = workoutExerciseRepo;
//    }
//    @Transactional
//    public Long addExercise(String ownerId, Long workoutId, AddExerciseToWorkoutRequest req) {
//        // Implementation for adding exercise to workout
//        Workout workout = workoutRepo.findByIdAndUserId(workoutId, Long.parseLong(ownerId))
//            .orElseThrow(() -> new RuntimeException("Workout not found"));
//        Exercise exercise = exerciseRepo.findById(req.getExerciseId())
//            .orElseThrow(() -> new RuntimeException("Exercise not found"));
//
//        WorkoutExercise entry = new WorkoutExercise(workout, exercise, req.getSets(), req.getReps());
//        workoutExerciseRepo.save(entry);
//        return entry.getId();
//    }
//}
