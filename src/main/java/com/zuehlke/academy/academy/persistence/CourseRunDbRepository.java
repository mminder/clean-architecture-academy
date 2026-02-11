package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.academy.domain.CourseRun;
import com.zuehlke.academy.academy.domain.Lesson;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class CourseRunDbRepository implements CourseRunRepository {

    @Override
    public CourseRun findById(UUID id) {
        Lesson lesson1 = new Lesson(
                "Getting Started",
                LocalDateTime.of(2026, 3, 10, 9, 0),
                LocalDateTime.of(2026, 3, 10, 12, 0)
        );
        Lesson lesson2 = new Lesson(
                "Core Concepts",
                LocalDateTime.of(2026, 3, 10, 13, 0),
                LocalDateTime.of(2026, 3, 10, 17, 0)
        );
        return new CourseRun(id, 20, List.of(lesson1, lesson2), Collections.emptyList());
    }

    @Override
    public void update(CourseRun courseRun) {
        // No-op sample implementation
    }
}
