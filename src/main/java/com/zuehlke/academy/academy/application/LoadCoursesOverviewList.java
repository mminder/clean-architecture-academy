package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.CourseOverviewReadModelRepository;
import com.zuehlke.academy.academy.application.dto.CourseOverviewResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fetches an "overview list" of all courses.
 */
@Service
public class LoadCoursesOverviewList {

    private final CourseOverviewReadModelRepository courseOverviewReadModelRepository;

    public LoadCoursesOverviewList(CourseOverviewReadModelRepository courseOverviewReadModelRepository) {
        this.courseOverviewReadModelRepository = courseOverviewReadModelRepository;
    }

    public List<CourseOverviewResponse> execute() {
        return courseOverviewReadModelRepository.findAllCourseOverviews();
    }
}
