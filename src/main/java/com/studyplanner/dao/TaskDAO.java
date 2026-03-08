package com.studyplanner.dao;

import java.util.List;
import com.studyplanner.domain.Task;

public interface TaskDAO {
    void saveTasks(List<Task> tasks);
    List<Task> loadTasks();
}
