package com.studyplanner.domain;

import java.time.LocalDate;

public class Task {
    private final String name;
    private final int durationHours;
    private final LocalDate deadline;
    private final int priority;
    private final String type;

    public Task(String name, int durationHours, LocalDate deadline, int priority, String type) {
        this.name = name;
        this.durationHours = durationHours;
        this.deadline = deadline;
        this.priority = priority;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + ": " + name + " | deadline=" + deadline + " | duration=" + durationHours + "h | priority=" + priority;
    }
}