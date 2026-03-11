package com.group9.fitnesstracker.repository;

//import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group9.fitnesstracker.entities.Workout;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    Optional<Workout> findByIdAndUserId(Long id, Long userId);
    
}
