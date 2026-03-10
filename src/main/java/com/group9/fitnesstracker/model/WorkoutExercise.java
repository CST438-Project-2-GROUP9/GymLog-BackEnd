package com.group9.fitnesstracker.model;

//import jakarta.annotation.Generated;
import jakarta.persistence.*;

@Entity
@Table(name = "workout_exercises")
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workout_exercises_id")
    private Long id;

    @Column(name = "workout_id", nullable = false)
    private Long workoutId;

    @Column(name = "exercise_id", nullable = false)
    private Long exerciseId;

    @Column(name = "sets")
    private int sets;

    @Column(name = "reps")
    private int reps;


    public WorkoutExercise() {}

    public WorkoutExercise(Long workoutId, Long exerciseId, int sets, int reps) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.sets = sets;
        this.reps = reps;
    }
    public Long getId() {
        return id;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

}
