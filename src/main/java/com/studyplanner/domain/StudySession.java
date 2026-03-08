package com.studyplanner.domain;

public class StudySession {
    private final Task task;
    private final int plannedHours;

    public StudySession(Task task, int plannedHours) {
        this.task = task;
        this.plannedHours = plannedHours;
    }

    public Task getTask() {
        return task;
    }

    public int getPlannedHours() {
        return plannedHours;
    }

    @Override
    public String toString() {
        return task.getName() + " - " + plannedHours + "h";
    }
}
