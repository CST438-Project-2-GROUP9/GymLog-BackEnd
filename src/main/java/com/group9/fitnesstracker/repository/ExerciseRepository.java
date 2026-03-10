package com.group9.fitnesstracker.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.group9.fitnesstracker.model.Exercise;
import org.springframework.data.jpa.repository.Query;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query(value = "SELECT * FROM \"exercise\" WHERE \"exercise_id\" = :id", nativeQuery = true)
    Exercise getByExerciseId(Long id);
}
