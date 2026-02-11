package com.zuehlke.academy.academy.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Response object containing information about one course")
public record CourseResponse(
        @Schema(description = "Unique identifier of the course", example = "123e4567-e89b-12d3-a456-426614174000")
        String id,

        @Schema(description = "Name of the course", example = "Spring Boot Fundamentals")
        String name,

        @Schema(description = "Detailed description of the course", example = "Learn the basics of Spring Boot framework")
        String description,

        @Schema(description = "Date of the next course run in ISO format", example = "2024-09-01")
        String nextRunDate,

        @Schema(description = "List of all course runs", example = "['e6b9f1d7-014c-4f4f-bea2-599d0f6516f6']")
        List<String> courseRunIds
) {
}
