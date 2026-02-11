package com.zuehlke.academy.academy.application.readmodel;

import com.zuehlke.academy.academy.domain.CourseRun;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CourseOfferingReadModel(UUID courseId,
                                      String name,
                                      String description,
                                      // TODO DISCUSS: fine to use domain here?
                                      List<CourseRun> courseRuns) {

    public LocalDateTime nextCourseRunStartTime() {
        return courseRuns.stream()
                .map(CourseRun::startTime)
                .filter(java.util.Objects::nonNull)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }
}