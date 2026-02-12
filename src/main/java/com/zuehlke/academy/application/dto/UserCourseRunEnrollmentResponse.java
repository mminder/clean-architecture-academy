package com.zuehlke.academy.application.dto;

import com.zuehlke.academy.domain.courseRun.EnrollmentStatus;

import java.time.Instant;
import java.util.UUID;

public record UserCourseRunEnrollmentResponse(UUID enrollmentId,
                                              UUID courseRunId,
                                              UUID userId,
                                              EnrollmentStatus enrollmentStatus,
                                              Instant enrolledAt) {

}