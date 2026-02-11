package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import com.zuehlke.academy.academy.shared.validation.Validation;

import java.time.LocalDateTime;

public class Lesson {
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Lesson(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = Validation.notBlank(description, "Lesson description must not be blank");
        this.startTime = Validation.notNull(startTime, "Lesson startTime must not be null");
        this.endTime = Validation.notNull(endTime, "Lesson endTime must not be null");
        if (endTime.isBefore(startTime) || endTime.isEqual(startTime)) {
            throw new ApplicationException("Lesson endTime must be after startTime");
        }
    }
}
