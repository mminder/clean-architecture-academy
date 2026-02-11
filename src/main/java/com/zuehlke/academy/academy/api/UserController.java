package com.zuehlke.academy.academy.api;

import com.zuehlke.academy.academy.api.dto.UserEnrollmentResponse;
import com.zuehlke.academy.academy.application.LoadUserCourseRunEnrollments;
import com.zuehlke.academy.academy.application.readmodel.UserCourseRunEnrollmentReadModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Enrollments", description = "User enrollment management API")
public class UserController {

    private final LoadUserCourseRunEnrollments loadUserCourseRunEnrollments;

    public UserController(LoadUserCourseRunEnrollments loadUserCourseRunEnrollments) {
        this.loadUserCourseRunEnrollments = loadUserCourseRunEnrollments;
    }

    @GetMapping("/{userId}/enrollments")
    @Operation(summary = "Get all enrollments for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user enrollments")
    })
    public List<UserEnrollmentResponse> getUserEnrollments(@PathVariable String userId) {
        List<UserCourseRunEnrollmentReadModel> enrollments = loadUserCourseRunEnrollments.execute(UUID.fromString(userId));
        return enrollments.stream()
                .map(this::mapToUserEnrollmentResponse)
                .toList();
    }

    private UserEnrollmentResponse mapToUserEnrollmentResponse(UserCourseRunEnrollmentReadModel enrollment) {
        return new UserEnrollmentResponse(
                enrollment.enrollmentId().toString(),
                enrollment.userId().toString(),
                enrollment.courseRunId().toString()
        );
    }
}
