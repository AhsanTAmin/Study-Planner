package com.studyplanner.intelligence;

import com.studyplanner.domain.Task;

public class TaskRecommendation {
    private final Task task;
    private final double score;
    private final String explanation;

    public TaskRecommendation(Task task, double score, String explanation) {
        this.task = task;
        this.score = score;
        this.explanation = explanation;
    }

    public Task getTask() {
        return task;
    }

    public double getScore() {
        return score;
    }

    public String getExplanation() {
        return explanation;
    }
}
