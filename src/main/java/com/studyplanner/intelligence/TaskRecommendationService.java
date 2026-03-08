package com.studyplanner.intelligence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.studyplanner.domain.Task;

public class TaskRecommendationService {

    private final TaskScorer taskScorer;

    public TaskRecommendationService(TaskScorer taskScorer) {
        this.taskScorer = taskScorer;
    }

    public List<TaskRecommendation> recommendTasks(List<Task> tasks) {
        List<TaskRecommendation> recommendations = new ArrayList<>();

        for (Task task : tasks) {
            recommendations.add(taskScorer.score(task));
        }

        recommendations.sort(Comparator.comparingDouble(TaskRecommendation::getScore).reversed());
        return recommendations;
    }
}
