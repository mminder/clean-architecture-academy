package com.zuehlke.academy.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("lessons")
public class LessonEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    private String description;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public LessonEntity(UUID id, Long version, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.version = version;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
