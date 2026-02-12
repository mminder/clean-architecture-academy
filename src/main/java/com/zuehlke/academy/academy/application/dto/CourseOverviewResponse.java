package com.zuehlke.academy.academy.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CourseOverviewResponse(UUID courseId,
                                     String name,
                                     String description,
                                     LocalDateTime nextCourseRunStartTime) {
}