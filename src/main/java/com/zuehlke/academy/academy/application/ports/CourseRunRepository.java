package com.zuehlke.academy.academy.application.ports;

import com.zuehlke.academy.academy.domain.CourseRun;

import java.util.UUID;

public interface CourseRunRepository {
    CourseRun findById(UUID id);

    void update(CourseRun courseRun);
}
