package com.zuehlke.academy.domain;

import java.util.UUID;

public class CourseRun {
    private final UUID id;
    private final Long version;
    private final int maxParticipants;
    private UUID trainerId;

    public CourseRun(UUID id, Long version, int maxParticipants, UUID trainerId) {
        this.id = id;
        this.version = version;
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

    public Long version() {
        return version;
    }

    public void changeTrainer(UUID trainerProfileId) {
        this.trainerId = trainerProfileId;
    }
}
