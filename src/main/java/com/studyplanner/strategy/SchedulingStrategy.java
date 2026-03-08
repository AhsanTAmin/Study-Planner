package com.studyplanner.strategy;

import java.util.List;

import com.studyplanner.domain.Task;

public interface SchedulingStrategy {
    List<Task> generatePlan(List<Task> tasks);
}