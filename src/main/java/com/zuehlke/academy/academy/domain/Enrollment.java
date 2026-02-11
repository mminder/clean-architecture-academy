package com.zuehlke.academy.academy.domain;

import java.time.Instant;
import java.util.UUID;

public class Enrollment {
    private final UUID id;
    private final UUID userId;
    private final EnrollmentStatus status;
    private final Instant createdAt;

    public Enrollment(UUID id, UUID userId, EnrollmentStatus status, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID id() {
        return id;
    }

    public UUID userId() {
        return userId;
    }

    public boolean isConfirmed() {
        return status == EnrollmentStatus.CONFIRMED;
    }
}
