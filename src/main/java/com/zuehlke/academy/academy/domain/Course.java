package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.validation.Validation;

import java.util.List;
import java.util.UUID;

public class Course {
    public final UUID id;
    public final String name;
    public final String description;
    public final List<UUID> courseRunIds;

    public Course(UUID id, String name, String description, List<UUID> courseRunIds) {
        this.id = Validation.notNull(id, "Course id must not be null");
        this.name = Validation.notBlank(name, "Course name must not be blank");
        this.description = Validation.notNull(description, "Course description must not be null");
        this.courseRunIds = Validation.notNull(courseRunIds, "Course courseRunIds must not be null");
    }
}
