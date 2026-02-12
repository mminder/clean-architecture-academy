package com.zuehlke.academy.domain;

import java.time.Instant;
import java.util.UUID;

public class Enrollment {
    private final UUID id;
    private final UUID userId;
    private final UUID courseRunId;
    private final EnrollmentStatus status;
    private final Instant createdAt;

    public Enrollment(UUID id, UUID userId, UUID courseRunId, EnrollmentStatus status, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.courseRunId = courseRunId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Enrollment create(UUID userId, UUID courseRunId) {
        return new Enrollment(UUID.randomUUID(), userId, courseRunId, EnrollmentStatus.CONFIRMED, Instant.now());
    }

    public UUID id() {
        return id;
    }

    public UUID userId() {
        return userId;
    }

    public UUID courseRunId() {
        return courseRunId;
    }

    public EnrollmentStatus status() {
        return status;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public boolean isConfirmed() {
        return status == EnrollmentStatus.CONFIRMED;
    }
}
