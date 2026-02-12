package com.zuehlke.academy.application.ports;

import com.zuehlke.academy.domain.CourseRun;

import java.util.UUID;

public interface CourseRunRepository {
    CourseRun findById(UUID id);

    void update(CourseRun courseRun);
}
