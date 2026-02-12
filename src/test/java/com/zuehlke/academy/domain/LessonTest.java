package com.zuehlke.academy.domain;

import com.zuehlke.academy.shared.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LessonTest {

    // description validation tests
    @Test
    void shouldCreateLessonWithValidDescription() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);

        assertDoesNotThrow(() -> new Lesson("Java Basics", start, end));
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Lesson(null, start, end)
        );
        assertEquals("Lesson description must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsEmpty() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Lesson("", start, end)
        );
        assertEquals("Lesson description must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsBlank() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Lesson("   ", start, end)
        );
        assertEquals("Lesson description must not be blank", exception.getMessage());
    }

    // startTime validation tests
    @Test
    void shouldThrowExceptionWhenStartTimeIsNull() {
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Lesson("Java Basics", null, end)
        );
        assertEquals("Lesson startTime must not be null", exception.getMessage());
    }

    // endTime validation tests
    @Test
    void shouldThrowExceptionWhenEndTimeIsNull() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Lesson("Java Basics", start, null)
        );
        assertEquals("Lesson endTime must not be null", exception.getMessage());
    }

    // endTime must be after startTime tests
    @Test
    void shouldThrowExceptionWhenEndTimeIsBeforeStartTime() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 12, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 9, 0);

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Lesson("Java Basics", start, end)
        );
        assertEquals("Lesson endTime must be after startTime", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEndTimeEqualsStartTime() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 9, 0);

        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Lesson("Java Basics", start, end)
        );
        assertEquals("Lesson endTime must be after startTime", exception.getMessage());
    }

    // Valid time range tests
    @Test
    void shouldCreateLessonWithValidTimeRange() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);

        assertDoesNotThrow(() -> new Lesson("Java Basics", start, end));
    }

    @Test
    void shouldCreateLessonWithShortDuration() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 9, 1); // 1 minute lesson

        assertDoesNotThrow(() -> new Lesson("Quick intro", start, end));
    }

    @Test
    void shouldCreateLessonWithLongDuration() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 2, 18, 0); // Multi-day lesson

        assertDoesNotThrow(() -> new Lesson("Workshop", start, end));
    }

    @Test
    void shouldCreateLessonSpanningMultipleDays() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 5, 17, 0);

        assertDoesNotThrow(() -> new Lesson("Intensive Bootcamp", start, end));
    }

    // Edge case tests
    @Test
    void shouldCreateLessonWithSpecialCharactersInDescription() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);

        assertDoesNotThrow(() -> new Lesson("C++ & Java: Pointers vs References!", start, end));
    }

    @Test
    void shouldCreateLessonWithUnicodeCharacters() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);

        assertDoesNotThrow(() -> new Lesson("学习Java编程 🚀", start, end));
    }
}
