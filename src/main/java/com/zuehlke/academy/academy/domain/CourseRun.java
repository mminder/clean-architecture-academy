package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.validation.Validation;

import java.time.LocalDateTime;
import java.util.List;

public class CourseRun {
    private final int maxParticipants;
    private final List<Lesson> lessons;

    public CourseRun(int maxParticipants, List<Lesson> lessons) {
        this.maxParticipants = Validation.minInt(maxParticipants, 1, "CourseRun maxParticipants must be at least 1");
        this.lessons = Validation.notNull(lessons, "CourseRun lessons must not be null");
    }

    public LocalDateTime startTime() {
        return lessons.stream()
                .map(Lesson::startTime)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }


}
