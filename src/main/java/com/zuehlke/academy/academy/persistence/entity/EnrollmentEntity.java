package com.zuehlke.academy.academy.persistence.entity;

import com.zuehlke.academy.academy.domain.EnrollmentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("enrollments")
public class EnrollmentEntity {

    @Id
    private UUID id;

    private UUID userId;

    private EnrollmentStatus status;

    private Instant createdAt;

    public EnrollmentEntity(UUID id, UUID userId, EnrollmentStatus status, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
