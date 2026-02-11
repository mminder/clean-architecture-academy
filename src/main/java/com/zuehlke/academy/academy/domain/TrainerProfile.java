package com.zuehlke.academy.academy.domain;

import java.util.UUID;

public class TrainerProfile {
    private final UUID id;
    private final String description;
    private final User user;

    public TrainerProfile(UUID id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public User user() {
        return user;
    }
}
