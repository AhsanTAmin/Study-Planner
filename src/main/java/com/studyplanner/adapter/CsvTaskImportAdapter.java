package com.studyplanner.adapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.studyplanner.domain.Task;
import com.studyplanner.factory.TaskFactory;

public class CsvTaskImportAdapter implements TaskImportAdapter {

    private final TaskFactory taskFactory;

    public CsvTaskImportAdapter(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    @Override
    public List<Task> importTasks(List<String> rawData) {
        List<Task> tasks = new ArrayList<>();

        for (String line : rawData) {
            String[] parts = line.split(",");

            String type = parts[0].trim();
            String name = parts[1].trim();
            int durationHours = Integer.parseInt(parts[2].trim());
            LocalDate deadline = LocalDate.parse(parts[3].trim());
            int priority = Integer.parseInt(parts[4].trim());

            Task task = taskFactory.createTask(type, name, durationHours, deadline, priority);
            tasks.add(task);
        }

        return tasks;
    }
}
