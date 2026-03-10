package com.group9.fitnesstracker.repository;

import com.group9.fitnesstracker.model.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    @Query(value = "SELECT we.\"workout_exercises_id\" AS workoutExerciseId, we.sets AS sets, we.reps AS reps, e.name AS exerciseName FROM \"workout_exercises\" we JOIN \"exercise\" e ON we.exercise_id = e.exercise_id WHERE we.workout_id = :workoutId", nativeQuery = true)
    List<Map<String, Object>> findExercisesWithNamesByWorkoutId(@Param("workoutId") Long workoutId);
}