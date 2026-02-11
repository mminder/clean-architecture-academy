package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.CourseRepository;
import com.zuehlke.academy.academy.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Fetches an "overview list" of all courses.
 */
@Service
public class LoadAllCourses {

    private final CourseRepository courseRepository;

    public LoadAllCourses(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> execute() {
        // TODO DISCUSS: this fetches all courses with all their CourseRuns, Enrollments, etc. Maybe a read model is better here for performance reasons?
        return courseRepository.findAllCourses();
    }
}
