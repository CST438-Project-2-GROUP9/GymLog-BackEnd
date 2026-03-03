package com.group9.fitnesstracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "workout_exercises", 
    uniqueConstraints = @UniqueConstraint(columnNames = {"workout_id", "exercise_id"})
)
public class WorkoutExercise {
    

    
}
