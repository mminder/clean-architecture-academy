package com.zuehlke.academy.persistence.entity;

import com.zuehlke.academy.domain.EnrollmentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("enrollments")
public class EnrollmentEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    private UUID userId;

    private EnrollmentStatus status;

    private Instant createdAt;

    private UUID courseRunId;

    public EnrollmentEntity(UUID id, Long version, UUID userId, UUID courseRunId, EnrollmentStatus status, Instant createdAt) {
        this.id = id;
        this.version = version;
        this.userId = userId;
        this.courseRunId = courseRunId;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
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

    public UUID getCourseRunId() {
        return courseRunId;
    }
}
