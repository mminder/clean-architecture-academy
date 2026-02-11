package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.CourseRepository;
import com.zuehlke.academy.academy.domain.Course;
import com.zuehlke.academy.academy.domain.CourseRun;
import com.zuehlke.academy.academy.domain.Lesson;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class CourseDbRepository implements CourseRepository {
    @Override
    public List<Course> findAll() {
        Lesson lesson1 = new Lesson(
                "Introduction to Java",
                LocalDateTime.of(2026, 3, 1, 9, 0),
                LocalDateTime.of(2026, 3, 1, 12, 0)
        );
        Lesson lesson2 = new Lesson(
                "Spring Boot Basics",
                LocalDateTime.of(2026, 3, 1, 13, 0),
                LocalDateTime.of(2026, 3, 1, 17, 0)
        );
        CourseRun courseRun = new CourseRun(20, List.of(lesson1, lesson2));
        Course course = new Course(UUID.randomUUID(), "Spring Boot for Beginners", "A complete beginner course", List.of(courseRun));
        return List.of(course);
    }
}
