package com.group9.fitnesstracker.repository;

//import java.lang.foreign.Linker.Option;
import java.util.List;
import java.util.Optional;

import com.group9.fitnesstracker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fitnesstracker.model.Workout;
import org.springframework.data.jpa.repository.Query;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
//    Optional<Workout> findByIdAndUserId(Long id, Long userId);

    //  Retrieves a single Workout entity by its unique workout_id
    @Query(value = "SELECT * FROM \"workout\" WHERE \"workout_id\" = :id", nativeQuery = true)
    Workout getByWorkoutId(Long id);
}
