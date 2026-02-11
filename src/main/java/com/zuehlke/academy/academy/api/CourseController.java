package com.zuehlke.academy.academy.api;

import com.zuehlke.academy.academy.api.dto.CourseOfferingResponse;
import com.zuehlke.academy.academy.application.LoadAllCourseOfferings;
import com.zuehlke.academy.academy.domain.Course;
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

    private final LoadAllCourseOfferings loadAllCourseOfferings;

    public CourseController(LoadAllCourseOfferings loadAllCourseOfferings) {
        this.loadAllCourseOfferings = loadAllCourseOfferings;
    }

    @GetMapping
    @Operation(summary = "Get all courses", description = "Retrieves a list of all available course offerings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of courses")
    })
    public List<CourseOfferingResponse> getAllCourses() {
        List<Course> courses = loadAllCourseOfferings.execute();
        return courses.stream()
                .map(course -> new CourseOfferingResponse(
                        course.id.toString(),
                        course.name,
                        course.description
                ))
                .toList();
    }
}
