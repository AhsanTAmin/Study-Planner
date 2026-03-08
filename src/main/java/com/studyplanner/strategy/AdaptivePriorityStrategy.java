package com.studyplanner.strategy;

import java.util.ArrayList;
import java.util.List;

import com.studyplanner.domain.Task;
import com.studyplanner.intelligence.TaskRecommendation;
import com.studyplanner.intelligence.TaskRecommendationService;

public class AdaptivePriorityStrategy implements SchedulingStrategy {

    private final TaskRecommendationService recommendationService;

    public AdaptivePriorityStrategy(TaskRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Override
    public List<Task> generatePlan(List<Task> tasks) {
        List<TaskRecommendation> recommendations = recommendationService.recommendTasks(tasks);
        List<Task> orderedTasks = new ArrayList<>();

        for (TaskRecommendation recommendation : recommendations) {
            orderedTasks.add(recommendation.getTask());
        }

        return orderedTasks;
    }
}
