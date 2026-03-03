package com.group9.fitnesstracker.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "workout_exercises", 
    uniqueConstraints = @UniqueConstraint(columnNames = {"workout_id", "exercise_id"})
)
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;
    private Integer sets = 3;
    private Integer reps = 10;
    public WorkoutExercise() {
    }
    public WorkoutExercise(Workout workout, Exercise exercise, Integer sets, Integer reps) {
        this.workout = workout;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }
    public Long getId() {
        return id;
    }
    public Workout getWorkout() {
        return workout;
    }
    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
    public Exercise getExercise() {
        return exercise;
    }
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
    public Integer getSets() {
        return sets;
    }
    public void setSets(Integer sets) {
        this.sets = sets;
    }
    public Integer getReps() {
        return reps;
    }
    public void setReps(Integer reps) {
        this.reps = reps;
    }

}
