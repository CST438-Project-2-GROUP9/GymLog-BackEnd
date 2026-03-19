package com.group9.fitnesstracker;

import com.group9.fitnesstracker.entities.Exercise;
import com.group9.fitnesstracker.services.ExerciseService;
import com.group9.fitnesstracker.services.WorkoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.jayway.jsonpath.JsonPath;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockitoBean
    private ExerciseService exerciseService;

    // was having a dependency issue where the application context wouldn't load for this test because Workout Service didn't exist
    @MockitoBean
    private WorkoutService workoutService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllExercises() throws Exception {
        // ARRANGE
        Exercise e1 = new Exercise("Push Up", "Push Up Description", "Upper");
        Mockito.when(exerciseService.getAllExercises()).thenReturn(List.of(e1));

        // ACT
        MvcResult result = mockMvc.perform(get("/api/exercises"))
                .andExpect(status().isOk())
                .andReturn();

        // ASSERT
        String content = result.getResponse().getContentAsString();
        String exerciseName = JsonPath.read(content, "$[0].name");
        String muscleGroup = JsonPath.read(content, "$[0].bodyRegion");

        assertEquals("Push Up", exerciseName);
        assertEquals("Upper", muscleGroup);
    }
}
