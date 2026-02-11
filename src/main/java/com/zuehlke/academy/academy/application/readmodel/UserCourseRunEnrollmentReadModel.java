package com.zuehlke.academy.academy.application.readmodel;

import java.util.UUID;

public record UserCourseRunEnrollmentReadModel(UUID enrollmentId,
                                               UUID courseRunId,
                                               UUID userId) {

}