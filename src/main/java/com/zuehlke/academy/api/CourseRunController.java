package com.zuehlke.academy.api;

import com.zuehlke.academy.api.dto.CourseRunEnrollmentRequest;
import com.zuehlke.academy.application.dto.CourseRunDetailsResponse;
import com.zuehlke.academy.application.EnrollUserInCourseRun;
import com.zuehlke.academy.application.LoadCourseRunDetails;
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

    private final LoadCourseRunDetails loadCourseRunDetails;
    private final EnrollUserInCourseRun enrollUserInCourseRun;

    public CourseRunController(LoadCourseRunDetails loadCourseRunDetails, EnrollUserInCourseRun enrollUserInCourseRun) {
        this.loadCourseRunDetails = loadCourseRunDetails;
        this.enrollUserInCourseRun = enrollUserInCourseRun;
    }

    @GetMapping("/{courseRunId}")
    @Operation(summary = "Get a course run by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved course run")
    })
    public CourseRunDetailsResponse getCourseRunById(@PathVariable String courseRunId) {
        return this.loadCourseRunDetails.execute(UUID.fromString(courseRunId));
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
