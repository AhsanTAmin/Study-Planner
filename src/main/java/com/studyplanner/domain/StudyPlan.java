package com.studyplanner.domain;

import java.util.ArrayList;
import java.util.List;

public class StudyPlan {
    private final List<StudySession> sessions = new ArrayList<>();

    public void addSession(StudySession session) {
        sessions.add(session);
    }

    public List<StudySession> getSessions() {
        return new ArrayList<>(sessions);
    }
}
