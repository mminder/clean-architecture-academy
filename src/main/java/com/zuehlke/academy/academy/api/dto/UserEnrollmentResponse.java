package com.zuehlke.academy.academy.api.dto;

public record UserEnrollmentResponse(String enrollmentId, String userId, String courseRunId, String enrollmentStatus, String enrolledAtTimestamp) {
}
