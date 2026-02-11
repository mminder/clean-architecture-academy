package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseRunTest {

    // maxParticipants validation tests
    @Test
    void shouldCreateCourseRunWithValidMaxParticipants() {
        assertDoesNotThrow(() -> new CourseRun(1, new ArrayList<>()));
        assertDoesNotThrow(() -> new CourseRun(10, new ArrayList<>()));
        assertDoesNotThrow(() -> new CourseRun(100, new ArrayList<>()));
    }

    @Test
    void shouldThrowExceptionWhenMaxParticipantsIsZero() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new CourseRun(0, new ArrayList<>())
        );
        assertEquals("CourseRun maxParticipants must be at least 1", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMaxParticipantsIsNegative() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new CourseRun(-1, new ArrayList<>())
        );
        assertEquals("CourseRun maxParticipants must be at least 1", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMaxParticipantsIsLargeNegative() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new CourseRun(-100, new ArrayList<>())
        );
        assertEquals("CourseRun maxParticipants must be at least 1", exception.getMessage());
    }

    // lessons validation tests
    @Test
    void shouldThrowExceptionWhenLessonsIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new CourseRun(10, null)
        );
        assertEquals("CourseRun lessons must not be null", exception.getMessage());
    }

    @Test
    void shouldCreateCourseRunWithEmptyLessonsList() {
        assertDoesNotThrow(() -> new CourseRun(10, new ArrayList<>()));
    }

    @Test
    void shouldCreateCourseRunWithLessons() {
        // Given
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);
        Lesson lesson = new Lesson("Java Basics", start, end);
        List<Lesson> lessons = List.of(lesson);

        // When & Then
        assertDoesNotThrow(() -> new CourseRun(20, lessons));
    }

    @Test
    void shouldCreateCourseRunWithMultipleLessons() {
        // Given
        LocalDateTime start1 = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end1 = LocalDateTime.of(2026, 3, 1, 12, 0);
        LocalDateTime start2 = LocalDateTime.of(2026, 3, 2, 9, 0);
        LocalDateTime end2 = LocalDateTime.of(2026, 3, 2, 12, 0);

        Lesson lesson1 = new Lesson("Introduction", start1, end1);
        Lesson lesson2 = new Lesson("Advanced Topics", start2, end2);
        List<Lesson> lessons = List.of(lesson1, lesson2);

        // When & Then
        assertDoesNotThrow(() -> new CourseRun(15, lessons));
    }
}
