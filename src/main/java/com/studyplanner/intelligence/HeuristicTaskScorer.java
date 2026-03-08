package com.studyplanner.intelligence;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import com.studyplanner.domain.Task;

public class HeuristicTaskScorer implements TaskScorer {

    private final LocalDate referenceDate;

    public HeuristicTaskScorer(LocalDate referenceDate) {
        this.referenceDate = referenceDate;
    }

    @Override
    public TaskRecommendation score(Task task) {
        long daysUntilDeadline = ChronoUnit.DAYS.between(referenceDate, task.getDeadline());

        double deadlineScore;
        if (daysUntilDeadline < 0) {
            deadlineScore = 70.0;
        } else if (daysUntilDeadline == 0) {
            deadlineScore = 60.0;
        } else {
            deadlineScore = Math.max(0, 30 - daysUntilDeadline) * 2.0;
        }

        double manualPriorityScore = (6 - task.getPriority()) * 8.0;
        double durationScore = Math.min(task.getDurationHours(), 6) * 2.5;
        double typeScore = getTypeWeight(task.getType());

        double totalScore = deadlineScore + manualPriorityScore + durationScore + typeScore;

        String deadlineText;
        if (daysUntilDeadline < 0) {
            deadlineText = Math.abs(daysUntilDeadline) + " day(s) overdue";
        } else if (daysUntilDeadline == 0) {
            deadlineText = "due today";
        } else {
            deadlineText = "due in " + daysUntilDeadline + " day(s)";
        }

        String explanation = String.format(
            Locale.UK,
            "Score %.1f because the task is %s, duration is %dh, manual priority is %d, and task type '%s' adds contextual weight.",
            totalScore,
            deadlineText,
            task.getDurationHours(),
            task.getPriority(),
            task.getType()
        );

        return new TaskRecommendation(task, totalScore, explanation);
    }

    private double getTypeWeight(String taskType) {
        return switch (taskType.toLowerCase()) {
            case "coding" -> 8.0;
            case "revision" -> 6.0;
            case "reading" -> 4.0;
            case "examprep" -> 10.0;
            default -> 5.0;
        };
    }
}
