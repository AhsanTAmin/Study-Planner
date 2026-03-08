package com.studyplanner.intelligence;

import com.studyplanner.domain.Task;

public interface TaskScorer {
    TaskRecommendation score(Task task);
}
