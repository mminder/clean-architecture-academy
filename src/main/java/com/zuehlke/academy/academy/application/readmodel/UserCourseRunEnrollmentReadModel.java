package com.zuehlke.academy.academy.application.readmodel;

import com.zuehlke.academy.academy.domain.EnrollmentStatus;

import java.time.Instant;
import java.util.UUID;

public record UserCourseRunEnrollmentReadModel(UUID enrollmentId,
                                               UUID courseRunId,
                                               UUID userId,
                                               EnrollmentStatus enrollmentStatus,
                                               Instant enrolledAt) {

}