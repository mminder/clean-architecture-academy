package com.zuehlke.academy.academy.api;

import com.zuehlke.academy.academy.api.dto.CourseRunEnrollmentRequest;
import com.zuehlke.academy.academy.api.dto.CourseRunResponse;
import com.zuehlke.academy.academy.application.EnrollUserInCourseRun;
import com.zuehlke.academy.academy.application.LoadCourseRun;
import com.zuehlke.academy.academy.domain.CourseRun;
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

    private final LoadCourseRun loadCourseRun;
    private final EnrollUserInCourseRun enrollUserInCourseRun;

    public CourseRunController(LoadCourseRun loadCourseRun, EnrollUserInCourseRun enrollUserInCourseRun) {
        this.loadCourseRun = loadCourseRun;
        this.enrollUserInCourseRun = enrollUserInCourseRun;
    }

    @GetMapping("/{courseRunId}")
    @Operation(summary = "Get a course run by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved course run")
    })
    public CourseRunResponse getCourseRunById(@PathVariable String courseRunId) {
        CourseRun courseRun = this.loadCourseRun.execute(UUID.fromString(courseRunId));
        return new CourseRunResponse(courseRun.id().toString(), courseRun.trainer().user().name(), courseRun.availableSeats());
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
