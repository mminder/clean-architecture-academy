package com.zuehlke.academy.academy.application.readmodel;

import java.time.LocalDateTime;
import java.util.UUID;

public record CourseOverviewReadModel(UUID courseId,
                                      String name,
                                      String description,
                                      LocalDateTime nextRunDate) {
}
