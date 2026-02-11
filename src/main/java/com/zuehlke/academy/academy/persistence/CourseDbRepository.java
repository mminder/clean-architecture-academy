package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.CourseRepository;
import com.zuehlke.academy.academy.domain.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class CourseDbRepository implements CourseRepository {
    @Override
    public List<Course> findAll() {
        Course course = new Course(UUID.randomUUID(), "Course 1", "Description 1", List.of());
        return List.of(course);
    }
}
