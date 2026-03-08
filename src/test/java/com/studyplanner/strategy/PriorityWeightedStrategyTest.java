package com.studyplanner.strategy;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.studyplanner.domain.Task;

public class PriorityWeightedStrategyTest {

    @Test
    public void shouldSortTasksByPriorityThenDeadline() {
        List<Task> tasks = Arrays.asList(
            new Task("Task A", 2, LocalDate.of(2026, 3, 12), 2, "Reading"),
            new Task("Task B", 4, LocalDate.of(2026, 3, 10), 1, "Coding"),
            new Task("Task C", 1, LocalDate.of(2026, 3, 11), 3, "Revision")
        );

        SchedulingStrategy strategy = new PriorityWeightedStrategy();
        List<Task> result = strategy.generatePlan(tasks);

        assertEquals("Task B", result.get(0).getName());
        assertEquals("Task A", result.get(1).getName());
        assertEquals("Task C", result.get(2).getName());
    }
}
