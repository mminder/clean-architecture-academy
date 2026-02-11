package com.zuehlke.academy.academy.persistence;

import com.zuehlke.academy.academy.application.ports.CourseRunRepository;
import com.zuehlke.academy.academy.domain.*;
import com.zuehlke.academy.academy.domain.shared.Email;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        User user1 = new User(
                UUID.randomUUID(),
                List.of(Role.TRAINER),
                "John Doe",
                new Email("trainer@example.com")
        );
        TrainerProfile trainer1 = new TrainerProfile(
                UUID.randomUUID(),
                "Java and Spring Boot trainer",
                user1
        );
        return new CourseRun(id, 20, List.of(lesson1, lesson2), new ArrayList<>(), trainer1);
    }

    @Override
    public void update(CourseRun courseRun) {
        // No-op sample implementation
    }
}
