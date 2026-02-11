package com.zuehlke.academy.academy.api;

import com.zuehlke.academy.academy.api.dto.CourseOverviewResponse;
import com.zuehlke.academy.academy.application.LoadAllCourses;
import com.zuehlke.academy.academy.application.readmodel.CourseOverviewReadModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses", description = "Course management API")
public class CourseController {

    private final LoadAllCourses loadAllCourses;

    public CourseController(LoadAllCourses loadAllCourses) {
        this.loadAllCourses = loadAllCourses;
    }

    @GetMapping
    @Operation(summary = "Get all courses", description = "Retrieves a list of all available courses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of courses")
    })
    public List<CourseOverviewResponse> getAllCourses() {
        List<CourseOverviewReadModel> courses = loadAllCourses.execute();
        return courses.stream()
                .map(courseOverview -> new CourseOverviewResponse(
                        courseOverview.courseId().toString(),
                        courseOverview.name(),
                        courseOverview.description(),
                        toDateString(courseOverview.nextRunDate())
                ))
                .toList();
    }

    private String toDateString(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

}
