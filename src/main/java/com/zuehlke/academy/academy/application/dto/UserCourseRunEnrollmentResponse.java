package com.zuehlke.academy.academy.application.dto;

import com.zuehlke.academy.academy.domain.EnrollmentStatus;

import java.time.Instant;
import java.util.UUID;

public record UserCourseRunEnrollmentResponse(UUID enrollmentId,
                                              UUID courseRunId,
                                              UUID userId,
                                              EnrollmentStatus enrollmentStatus,
                                              Instant enrolledAt) {

}