package com.studyplanner.factory;

import java.time.LocalDate;

import com.studyplanner.domain.Task;

public class TaskFactory {

    public Task createTask(String type, String name, int durationHours, LocalDate deadline, int priority) {
        return new Task(name, durationHours, deadline, priority, type);
    }
}