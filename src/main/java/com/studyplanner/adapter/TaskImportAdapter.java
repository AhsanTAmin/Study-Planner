package com.studyplanner.adapter;

import java.util.List;
import com.studyplanner.domain.Task;

public interface TaskImportAdapter {
    List<Task> importTasks(List<String> rawData);
}
