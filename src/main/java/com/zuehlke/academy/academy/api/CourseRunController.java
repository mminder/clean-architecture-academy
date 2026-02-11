package com.zuehlke.academy.academy.api;

import com.zuehlke.academy.academy.api.dto.CourseRunEnrollmentRequest;
import com.zuehlke.academy.academy.application.EnrollUserInCourseRun;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/course-runs")
@Tag(name = "Course Runs", description = "Course run management API")
public class CourseRunController {

    private final EnrollUserInCourseRun enrollUserInCourseRun;

    public CourseRunController(EnrollUserInCourseRun enrollUserInCourseRun) {
        this.enrollUserInCourseRun = enrollUserInCourseRun;
    }

    @PostMapping("/{courseRunId}/enrollments")
    @Operation(summary = "Enroll a user in a course run")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User enrolled successfully")
    })
    public ResponseEntity<Void> enrollUserInCourseRun(@PathVariable String courseRunId, @RequestBody CourseRunEnrollmentRequest request) {
        enrollUserInCourseRun.execute(UUID.fromString(courseRunId), UUID.fromString(request.userId()));
        return ResponseEntity.noContent().build();
    }
}
