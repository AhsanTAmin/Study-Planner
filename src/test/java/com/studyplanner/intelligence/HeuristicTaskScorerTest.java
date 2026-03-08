package com.studyplanner.intelligence;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.studyplanner.domain.Task;

public class HeuristicTaskScorerTest {

    @Test
    public void shouldAssignHigherScoreToMoreUrgentTask() {
        HeuristicTaskScorer scorer = new HeuristicTaskScorer(LocalDate.of(2026, 3, 8));

        Task urgentTask = new Task("Urgent Task", 4, LocalDate.of(2026, 3, 10), 1, "Coding");
        Task lessUrgentTask = new Task("Less Urgent Task", 2, LocalDate.of(2026, 3, 15), 2, "Reading");

        TaskRecommendation urgentRecommendation = scorer.score(urgentTask);
        TaskRecommendation lessUrgentRecommendation = scorer.score(lessUrgentTask);

        assertTrue(urgentRecommendation.getScore() > lessUrgentRecommendation.getScore());
    }

    @Test
    public void shouldGenerateExplanationText() {
        HeuristicTaskScorer scorer = new HeuristicTaskScorer(LocalDate.of(2026, 3, 8));
        Task task = new Task("Explainable Task", 3, LocalDate.of(2026, 3, 11), 2, "Revision");

        TaskRecommendation recommendation = scorer.score(task);

        assertEquals(task, recommendation.getTask());
        assertTrue(recommendation.getExplanation().contains("Score"));
        assertTrue(recommendation.getExplanation().contains("manual priority"));
    }
}
