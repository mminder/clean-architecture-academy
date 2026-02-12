package com.zuehlke.academy.application.ports;

import com.zuehlke.academy.application.dto.CourseRunDetailsResponse;

import java.util.UUID;

public interface CourseRunDetailsQueryRepository {
    CourseRunDetailsResponse findCourseRunDetailsByCourseRunId(UUID courseRunId);
}
