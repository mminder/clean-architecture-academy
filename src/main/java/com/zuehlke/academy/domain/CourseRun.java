package com.zuehlke.academy.domain;

import java.util.UUID;

public class CourseRun {
    private final UUID id;
    private final int maxParticipants;
    private final UUID trainerId;

    public CourseRun(UUID id, int maxParticipants, UUID trainerId) {
        this.id = id;
        this.maxParticipants = maxParticipants;
        this.trainerId = trainerId;
    }

    public UUID id() {
        return id;
    }

    public int maxParticipants() {
        return maxParticipants;
    }

    public UUID trainerId() {
        return trainerId;
    }
}
