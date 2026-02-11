package com.zuehlke.academy.academy.application.ports;

import com.zuehlke.academy.academy.domain.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAllCourses();
}
