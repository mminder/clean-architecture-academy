package com.zuehlke.academy.academy.domain;

import java.util.UUID;

public class Enrollment {
    private final UUID id;
    private final User user;
    private final CourseRun courseRun;

    public Enrollment(UUID id, User user, CourseRun courseRun) {
        this.id = id;
        this.user = user;
        this.courseRun = courseRun;
    }
}
