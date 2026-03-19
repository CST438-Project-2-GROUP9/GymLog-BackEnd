package com.group9.fitnesstracker;

import com.group9.fitnesstracker.controllers.ExerciseController;
import com.group9.fitnesstracker.entities.Exercise;
import com.group9.fitnesstracker.services.ExerciseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * [Brief Description]
 *
 * @author: Dima Krayilo
 * @since: 3/18/2026
 * @version: 0.1.0
 *
 */

@SpringBootTest
public class ExerciseControllerTest {
    @InjectMocks
    private ExerciseController exerciseController;

    @Mock
    private ExerciseService exerciseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllExercises() {
        // ARRANGE
        Exercise e1 = new Exercise("Push Up", "Push Up Description", "Upper");
        Mockito.when(exerciseService.getAllExercises()).thenReturn(List.of(e1));

        // ACT
        ResponseEntity<List<Exercise>> response = exerciseController.getAllExercises();

        // ASSERT
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Push Up", response.getBody().get(0).getName());
    }
}
