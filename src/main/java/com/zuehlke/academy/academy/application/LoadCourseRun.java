package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.academy.domain.CourseRun;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Loads all details for a given course run, including enrollments and lessons.
 */
@Service
public class LoadCourseRun {

    private final CourseRunRepository courseRunRepository;

    public LoadCourseRun(CourseRunRepository courseRunRepository) {
        this.courseRunRepository = courseRunRepository;
    }

    public CourseRun execute(UUID courseRunId) {
        return courseRunRepository.findById(courseRunId);

    }
}
