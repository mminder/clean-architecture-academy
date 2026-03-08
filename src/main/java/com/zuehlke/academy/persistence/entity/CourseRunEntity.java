package com.zuehlke.academy.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("course_runs")
public class CourseRunEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    private int maxParticipants;

    private AggregateReference<TrainerEntity, UUID> trainer;

    public CourseRunEntity(UUID id, Long version, int maxParticipants,
                           AggregateReference<TrainerEntity, UUID> trainer) {
        this.id = id;
        this.version = version;
        this.maxParticipants = maxParticipants;
        this.trainer = trainer;
    }

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public AggregateReference<TrainerEntity, UUID> getTrainer() {
        return trainer;
    }

    public void setTrainer(AggregateReference<TrainerEntity, UUID> trainer) {
        this.trainer = trainer;
    }
}
