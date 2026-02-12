package com.zuehlke.academy.application.ports;

import com.zuehlke.academy.domain.courseRun.CourseRunAggregate;

import java.util.UUID;

public interface CourseRunRepository {
    CourseRunAggregate findById(UUID id);

    void update(CourseRunAggregate courseRunAggregate);
}
