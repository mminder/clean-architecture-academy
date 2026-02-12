package com.zuehlke.academy.application;

import com.zuehlke.academy.application.dto.CourseRunDetailsResponse;
import com.zuehlke.academy.application.ports.query.CourseRunDetailsQueryRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Loads all details for a given course run, including enrollments and lessons.
 */
@Service
public class LoadCourseRunDetails {

    private final CourseRunDetailsQueryRepository courseRunDetailsQueryRepository;

    public LoadCourseRunDetails(CourseRunDetailsQueryRepository courseRunDetailsQueryRepository) {
        this.courseRunDetailsQueryRepository = courseRunDetailsQueryRepository;
    }

    public CourseRunDetailsResponse execute(UUID courseRunId) {
        // TODO DISCUSS: alternatively, CourseRun, Enrollments and Trainer could be fetched separately via their own "pure" repositories.
        return courseRunDetailsQueryRepository.findCourseRunDetailsByCourseRunId(courseRunId);
    }
}
