package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.CourseRepository;
import com.zuehlke.academy.academy.application.readmodel.CourseOverviewReadModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fetches an "overview list" of all courses.
 */
@Service
public class LoadCoursesOverviewList {

    private final CourseRepository courseRepository;

    public LoadCoursesOverviewList(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseOverviewReadModel> execute() {
        return courseRepository.findAllCourseOverviews();
    }
}
