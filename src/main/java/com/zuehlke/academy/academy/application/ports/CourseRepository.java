package com.zuehlke.academy.academy.application.ports;

import com.zuehlke.academy.academy.application.readmodel.CourseOfferingReadModel;

import java.util.List;

public interface CourseRepository {
    List<CourseOfferingReadModel> findAllCourseOfferings();
}
