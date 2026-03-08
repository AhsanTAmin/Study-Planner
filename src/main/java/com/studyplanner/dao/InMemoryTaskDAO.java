package com.studyplanner.dao;

import java.util.ArrayList;
import java.util.List;
import com.studyplanner.domain.Task;

public class InMemoryTaskDAO implements TaskDAO {

    private final List<Task> storedTasks = new ArrayList<>();

    @Override
    public void saveTasks(List<Task> tasks) {
        storedTasks.clear();
        storedTasks.addAll(tasks);
    }

    @Override
    public List<Task> loadTasks() {
        return new ArrayList<>(storedTasks);
    }
}
