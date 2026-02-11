package com.zuehlke.academy.academy.application;

import com.zuehlke.academy.academy.application.ports.CourseRepository;
import com.zuehlke.academy.academy.application.readmodel.CourseOfferingReadModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadAllCourseOfferings {

    private final CourseRepository courseRepository;

    public LoadAllCourseOfferings(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<CourseOfferingReadModel> execute() {
        return courseRepository.findAllCourseOfferings();
    }
}
