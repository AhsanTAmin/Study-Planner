package com.studyplanner.dao;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.studyplanner.domain.Task;

public class InMemoryTaskDAOTest {

    @Test
    public void shouldSaveAndLoadTasks() {
        TaskDAO dao = new InMemoryTaskDAO();

        List<Task> tasks = Arrays.asList(
            new Task("Task A", 2, LocalDate.of(2026, 3, 12), 2, "Reading"),
            new Task("Task B", 4, LocalDate.of(2026, 3, 10), 1, "Coding")
        );

        dao.saveTasks(tasks);
        List<Task> loaded = dao.loadTasks();

        assertEquals(2, loaded.size());
        assertEquals("Task A", loaded.get(0).getName());
        assertEquals("Task B", loaded.get(1).getName());
    }
}
