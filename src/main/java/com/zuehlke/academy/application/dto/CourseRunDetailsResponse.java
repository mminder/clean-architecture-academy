package com.zuehlke.academy.application.dto;

public record CourseRunDetailsResponse(String courseId, String courseName, String courseRunId, String trainerName, int availableSeats) {
}
