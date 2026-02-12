package com.zuehlke.academy.application;

import com.zuehlke.academy.application.ports.CourseOverviewQueryRepository;
import com.zuehlke.academy.application.dto.CourseOverviewResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fetches an "overview list" of all courses.
 */
@Service
public class LoadCoursesOverviewList {

    private final CourseOverviewQueryRepository courseOverviewQueryRepository;

    public LoadCoursesOverviewList(CourseOverviewQueryRepository courseOverviewQueryRepository) {
        this.courseOverviewQueryRepository = courseOverviewQueryRepository;
    }

    public List<CourseOverviewResponse> execute() {
        return courseOverviewQueryRepository.findAllCourseOverviews();
    }
}
