package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.CourseRepository;
import com.zuehlke.academy.academy.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAllCourseOfferings {

    private final CourseRepository courseRepository;

    public LoadAllCourseOfferings(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> execute() {
        return courseRepository.findAll();
    }
}
