package com.zuehlke.academy.academy.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Table("courses")
public class CourseEntity {

    @Id
    private UUID id;

    @Version
    private Long version;

    private String name;

    private String description;

    @MappedCollection(idColumn = "course_id")
    private Set<AggregateReference<CourseRunEntity, UUID>> courseRuns;

    public CourseEntity(UUID id, Long version, String name, String description,
                        Set<AggregateReference<CourseRunEntity, UUID>> courseRuns) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.description = description;
        this.courseRuns = courseRuns;
    }

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<AggregateReference<CourseRunEntity, UUID>> getCourseRuns() {
        return courseRuns;
    }
}
