package com.studyplanner.intelligence;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.studyplanner.domain.Task;

public class TaskRecommendationServiceTest {

    @Test
    public void shouldReturnRecommendationsSortedByHighestScoreFirst() {
        HeuristicTaskScorer scorer = new HeuristicTaskScorer(LocalDate.of(2026, 3, 8));
        TaskRecommendationService service = new TaskRecommendationService(scorer);

        List<Task> tasks = Arrays.asList(
            new Task("Read Java", 2, LocalDate.of(2026, 3, 12), 2, "Reading"),
            new Task("Implement DAO", 4, LocalDate.of(2026, 3, 10), 1, "Coding"),
            new Task("Revise UML", 1, LocalDate.of(2026, 3, 11), 3, "Revision")
        );

        List<TaskRecommendation> recommendations = service.recommendTasks(tasks);

        assertEquals("Implement DAO", recommendations.get(0).getTask().getName());
        assertEquals("Read Java", recommendations.get(1).getTask().getName());
        assertEquals("Revise UML", recommendations.get(2).getTask().getName());
    }
}
