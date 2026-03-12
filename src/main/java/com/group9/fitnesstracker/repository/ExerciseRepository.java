package com.group9.fitnesstracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fitnesstracker.entities.Exercise;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value = "SELECT * FROM exercise WHERE body_region = :region ORDER BY RANDOM() LIMIT :limit", nativeQuery = true)
    List<Exercise> findRandomExercisesByBodyRegion(@Param("region") String region, @Param("limit") int limit);
}
