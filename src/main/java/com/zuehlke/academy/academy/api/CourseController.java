package com.zuehlke.academy.academy.api;

import com.zuehlke.academy.academy.application.LoadCoursesOverviewList;
import com.zuehlke.academy.academy.application.dto.CourseOverviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Course management API")
public class CourseController {

    private final LoadCoursesOverviewList loadCoursesOverviewList;

    public CourseController(LoadCoursesOverviewList loadCoursesOverviewList) {
        this.loadCoursesOverviewList = loadCoursesOverviewList;
    }

    @GetMapping
    @Operation(summary = "Get all courses", description = "Retrieves a list of all available courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of courses")
    })
    public List<CourseOverviewResponse> getAllCourses() {
        return loadCoursesOverviewList.execute();
    }
}
