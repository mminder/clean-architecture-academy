package com.zuehlke.academy.academy.domain;

import java.util.UUID;

public class TrainerProfile {
    private final UUID id;
    private final String description;
    private final UUID userId;

    public TrainerProfile(UUID id, String description, UUID userId) {
        this.id = id;
        this.description = description;
        this.userId = userId;
    }

    public UUID userId() {
        return userId;
    }
}
