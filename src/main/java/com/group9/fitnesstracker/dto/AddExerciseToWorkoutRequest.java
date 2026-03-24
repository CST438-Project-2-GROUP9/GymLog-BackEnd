package com.group9.fitnesstracker.dto;

import jakarta.validation.constraints.NotNull;

public class AddExerciseToWorkoutRequest {
    @NotNull
    private Long exerciseId;
    @NotNull
    private Integer sets;
    @NotNull
    private Integer reps;

    public AddExerciseToWorkoutRequest() {}

    public Long getExerciseId(){
        return exerciseId;
    }
    public void setExerciseId(Long exerciseId){
        this.exerciseId = exerciseId;
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
