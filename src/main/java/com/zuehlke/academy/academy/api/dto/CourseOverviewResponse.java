package com.zuehlke.academy.academy.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object containing overview information about one course")
public record CourseOverviewResponse(
        @Schema(description = "Unique identifier of the course", example = "123e4567-e89b-12d3-a456-426614174000")
        String id,

        @Schema(description = "Name of the course", example = "Spring Boot Fundamentals")
        String name,

        @Schema(description = "Detailed description of the course", example = "Learn the basics of Spring Boot framework")
        String description,

        @Schema(description = "Date of the next course run in ISO format", example = "2024-09-01")
        String nextRunDate
) {
}
