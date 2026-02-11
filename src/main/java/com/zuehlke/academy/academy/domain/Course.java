package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.validation.Validation;

import java.util.List;
import java.util.UUID;

public class Course {
    public final UUID id;
    public final String name;
    public final String description;
    public final List<CourseRun> courseRuns;

    public Course(UUID id, String name, String description, List<CourseRun> courseRuns) {
        this.id = Validation.notNull(id, "Course id must not be null");
        this.name = Validation.notBlank(name, "Course name must not be blank");
        this.description = Validation.notNull(description, "Course description must not be null");
        this.courseRuns = Validation.notNull(courseRuns, "Course courseRuns must not be null");
    }
}
