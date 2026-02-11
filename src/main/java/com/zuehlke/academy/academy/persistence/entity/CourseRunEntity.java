package com.zuehlke.academy.academy.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Table("course_runs")
public class CourseRunEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    private int maxParticipants;

    private AggregateReference<TrainerEntity, UUID> trainer;

    @MappedCollection(idColumn = "course_run_id")
    private Set<LessonEntity> lessons;

    @MappedCollection(idColumn = "course_run_id")
    private Set<EnrollmentEntity> enrollments;

    public CourseRunEntity(UUID id, Long version, int maxParticipants,
                          AggregateReference<TrainerEntity, UUID> trainer,
                          Set<LessonEntity> lessons, Set<EnrollmentEntity> enrollments) {
        this.id = id;
        this.version = version;
        this.maxParticipants = maxParticipants;
        this.trainer = trainer;
        this.lessons = lessons;
        this.enrollments = enrollments;
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

    public Set<LessonEntity> getLessons() {
        return lessons;
    }

    public Set<EnrollmentEntity> getEnrollments() {
        return enrollments;
    }
}
