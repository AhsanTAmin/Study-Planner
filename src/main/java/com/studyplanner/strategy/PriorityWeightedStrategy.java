package com.studyplanner.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.studyplanner.domain.Task;

public class PriorityWeightedStrategy implements SchedulingStrategy {

    @Override
    public List<Task> generatePlan(List<Task> tasks) {
        List<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(
            Comparator.comparing(Task::getPriority)
                      .thenComparing(Task::getDeadline)
        );
        return sortedTasks;
    }
}