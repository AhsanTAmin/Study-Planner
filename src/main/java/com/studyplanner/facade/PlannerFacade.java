package com.studyplanner.facade;

import java.util.List;

import com.studyplanner.dao.TaskDAO;
import com.studyplanner.domain.StudyPlan;
import com.studyplanner.domain.StudySession;
import com.studyplanner.domain.Task;
import com.studyplanner.strategy.SchedulingStrategy;

public class PlannerFacade {
    private final SchedulingStrategy schedulingStrategy;
    private final TaskDAO taskDAO;

    public PlannerFacade(SchedulingStrategy schedulingStrategy, TaskDAO taskDAO) {
        this.schedulingStrategy = schedulingStrategy;
        this.taskDAO = taskDAO;
    }

    public List<Task> generateStudyPlan(List<Task> tasks) {
        return schedulingStrategy.generatePlan(tasks);
    }

    public StudyPlan buildStudyPlan(List<Task> tasks) {
        List<Task> orderedTasks = schedulingStrategy.generatePlan(tasks);
        StudyPlan studyPlan = new StudyPlan();

        for (Task task : orderedTasks) {
            studyPlan.addSession(new StudySession(task, task.getDurationHours()));
        }

        return studyPlan;
    }

    public void saveTasks(List<Task> tasks) {
        taskDAO.saveTasks(tasks);
    }

    public List<Task> loadTasks() {
        return taskDAO.loadTasks();
    }
}