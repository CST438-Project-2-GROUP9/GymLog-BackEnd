package com.group9.fitnesstracker;

import com.group9.fitnesstracker.controllers.WorkoutController;
import com.group9.fitnesstracker.entities.Workout;
import com.group9.fitnesstracker.services.ExerciseService;
import com.group9.fitnesstracker.services.WorkoutService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Workout Controller test that tries to get all workouts
 *
 * @author: Dima Krayilo
 * @since: 3/18/2026
 * @version: 0.1.0
 *
 */
@SpringBootTest
public class WorkoutControllerTest {
    @InjectMocks
    private WorkoutController workoutController;

    @Mock
    private WorkoutService workoutService;

    @Mock
    private ExerciseService exerciseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllWorkouts() {
        Workout w1 = new Workout(1L, "Morning Run");
        Mockito.when(workoutService.getAllWorkouts()).thenReturn(Arrays.asList(w1));

        ResponseEntity<List<Workout>> response = workoutController.getAllWorkouts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Morning Run", response.getBody().get(0).getName());
    }
}
