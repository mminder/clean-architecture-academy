package com.zuehlke.academy.domain;

import java.util.UUID;

public class Trainer {
    private final UUID id;
    private final User user;
    private final String profileDescription;

    public Trainer(UUID id, User user, String profileDescription) {
        this.id = id;
        this.user = user;
        this.profileDescription = profileDescription;
    }
}
