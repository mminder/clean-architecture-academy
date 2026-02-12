package com.zuehlke.academy.application;

import com.zuehlke.academy.application.ports.query.CourseOverviewQueryRepository;
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
        // TODO DISCUSS: alternatively, CourseRuns and their Lessons could be fetched separately via their own "pure" repositories to compute next start date.
        // this could be done via a domain service
        return courseOverviewQueryRepository.findAllCourseOverviews();
    }
}
