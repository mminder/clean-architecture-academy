package com.zuehlke.academy.domain.courseRun;

import java.util.UUID;

public class CourseRun {
    private final UUID id;
    private final int maxParticipants;

    public CourseRun(UUID id, int maxParticipants) {
        this.id = id;
        this.maxParticipants = maxParticipants;
    }

    public UUID id() {
        return id;
    }

    public int maxParticipants() {
        return maxParticipants;
    }
}
