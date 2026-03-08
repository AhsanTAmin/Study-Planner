package com.studyplanner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.studyplanner.adapter.CsvTaskImportAdapter;
import com.studyplanner.adapter.TaskImportAdapter;
import com.studyplanner.dao.InMemoryTaskDAO;
import com.studyplanner.dao.TaskDAO;
import com.studyplanner.domain.StudyPlan;
import com.studyplanner.domain.StudySession;
import com.studyplanner.domain.Task;
import com.studyplanner.facade.PlannerFacade;
import com.studyplanner.factory.TaskFactory;
import com.studyplanner.intelligence.HeuristicTaskScorer;
import com.studyplanner.intelligence.TaskRecommendation;
import com.studyplanner.intelligence.TaskRecommendationService;
import com.studyplanner.strategy.AdaptivePriorityStrategy;
import com.studyplanner.strategy.EarliestDeadlineStrategy;
import com.studyplanner.strategy.PriorityWeightedStrategy;

public class App {
    public static void main(String[] args) {
        TaskFactory taskFactory = new TaskFactory();
        TaskDAO taskDAO = new InMemoryTaskDAO();
        TaskImportAdapter csvAdapter = new CsvTaskImportAdapter(taskFactory);

        List<String> csvLines = Arrays.asList(
            "Reading,Read Java chapter,2,2026-03-12,2",
            "Coding,Implement DAO layer,4,2026-03-10,1",
            "Revision,Revise UML diagrams,1,2026-03-11,3"
        );

        List<Task> importedTasks = csvAdapter.importTasks(csvLines);

        PlannerFacade deadlinePlanner = new PlannerFacade(new EarliestDeadlineStrategy(), taskDAO);
        PlannerFacade priorityPlanner = new PlannerFacade(new PriorityWeightedStrategy(), taskDAO);

        HeuristicTaskScorer taskScorer = new HeuristicTaskScorer(LocalDate.of(2026, 3, 8));
        TaskRecommendationService recommendationService = new TaskRecommendationService(taskScorer);
        PlannerFacade adaptivePlanner = new PlannerFacade(new AdaptivePriorityStrategy(recommendationService), taskDAO);

        deadlinePlanner.saveTasks(importedTasks);
        List<Task> loadedTasks = deadlinePlanner.loadTasks();

        System.out.println("Imported tasks via CSV Adapter:");
        for (Task task : loadedTasks) {
            System.out.println(task);
        }

        StudyPlan deadlinePlan = deadlinePlanner.buildStudyPlan(loadedTasks);
        StudyPlan priorityPlan = priorityPlanner.buildStudyPlan(loadedTasks);
        StudyPlan adaptivePlan = adaptivePlanner.buildStudyPlan(loadedTasks);

        System.out.println("\nStudyPlan using EarliestDeadlineStrategy:");
        for (StudySession session : deadlinePlan.getSessions()) {
            System.out.println(session);
        }

        System.out.println("\nStudyPlan using PriorityWeightedStrategy:");
        for (StudySession session : priorityPlan.getSessions()) {
            System.out.println(session);
        }

        System.out.println("\nAI-inspired task recommendations:");
        List<TaskRecommendation> recommendations = recommendationService.recommendTasks(loadedTasks);
        for (TaskRecommendation recommendation : recommendations) {
            System.out.println(recommendation.getTask().getName() + " -> score " + recommendation.getScore());
            System.out.println("  " + recommendation.getExplanation());
        }

        System.out.println("\nStudyPlan using AdaptivePriorityStrategy:");
        for (StudySession session : adaptivePlan.getSessions()) {
            System.out.println(session);
        }
    }
}
