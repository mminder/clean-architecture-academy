package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseRunTest {

    // maxParticipants validation tests
    @Test
    void shouldCreateCourseRunWithValidMaxParticipants() {
        assertDoesNotThrow(() -> new CourseRun(UUID.randomUUID(), 1, new ArrayList<>(), Collections.emptyList()));
        assertDoesNotThrow(() -> new CourseRun(UUID.randomUUID(), 10, new ArrayList<>(), Collections.emptyList()));
        assertDoesNotThrow(() -> new CourseRun(UUID.randomUUID(), 100, new ArrayList<>(), Collections.emptyList()));
    }

    @Test
    void shouldThrowExceptionWhenMaxParticipantsIsZero() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new CourseRun(UUID.randomUUID(), 0, new ArrayList<>(), Collections.emptyList())
        );
        assertEquals("CourseRun maxParticipants must be at least 1", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMaxParticipantsIsNegative() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new CourseRun(UUID.randomUUID(), -1, new ArrayList<>(), Collections.emptyList())
        );
        assertEquals("CourseRun maxParticipants must be at least 1", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMaxParticipantsIsLargeNegative() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new CourseRun(UUID.randomUUID(), -100, new ArrayList<>(), Collections.emptyList())
        );
        assertEquals("CourseRun maxParticipants must be at least 1", exception.getMessage());
    }

    // lessons validation tests
    @Test
    void shouldThrowExceptionWhenLessonsIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new CourseRun(UUID.randomUUID(), 10, null, Collections.emptyList())
        );
        assertEquals("CourseRun lessons must not be null", exception.getMessage());
    }

    @Test
    void shouldCreateCourseRunWithEmptyLessonsList() {
        assertDoesNotThrow(() -> new CourseRun(UUID.randomUUID(), 10, new ArrayList<>(), Collections.emptyList()));
    }

    @Test
    void shouldCreateCourseRunWithLessons() {
        // Given
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);
        Lesson lesson = new Lesson("Java Basics", start, end);
        List<Lesson> lessons = List.of(lesson);

        // When & Then
        assertDoesNotThrow(() -> new CourseRun(UUID.randomUUID(), 20, lessons, Collections.emptyList()));
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
        assertDoesNotThrow(() -> new CourseRun(UUID.randomUUID(), 15, lessons, Collections.emptyList()));
    }

    // startTime() method tests
    @Test
    void shouldReturnNullWhenNoLessons() {
        CourseRun courseRun = new CourseRun(UUID.randomUUID(), 10, List.of(), Collections.emptyList());

        assertNull(courseRun.startTime());
    }

    @Test
    void shouldReturnStartTimeOfSingleLesson() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 12, 0);
        Lesson lesson = new Lesson("Java Basics", start, end);
        CourseRun courseRun = new CourseRun(UUID.randomUUID(), 10, List.of(lesson), Collections.emptyList());

        assertEquals(start, courseRun.startTime());
    }

    @Test
    void shouldReturnEarliestStartTimeWhenMultipleLessons() {
        LocalDateTime start1 = LocalDateTime.of(2026, 3, 2, 14, 0);
        LocalDateTime end1 = LocalDateTime.of(2026, 3, 2, 17, 0);
        LocalDateTime start2 = LocalDateTime.of(2026, 3, 1, 9, 0); // Earliest
        LocalDateTime end2 = LocalDateTime.of(2026, 3, 1, 12, 0);
        LocalDateTime start3 = LocalDateTime.of(2026, 3, 3, 10, 0);
        LocalDateTime end3 = LocalDateTime.of(2026, 3, 3, 13, 0);

        Lesson lesson1 = new Lesson("Advanced Topics", start1, end1);
        Lesson lesson2 = new Lesson("Introduction", start2, end2);
        Lesson lesson3 = new Lesson("Conclusion", start3, end3);

        CourseRun courseRun = new CourseRun(UUID.randomUUID(), 15, List.of(lesson1, lesson2, lesson3), Collections.emptyList());

        assertEquals(start2, courseRun.startTime());
    }

    @Test
    void shouldReturnEarliestStartTimeRegardlessOfListOrder() {
        LocalDateTime start1 = LocalDateTime.of(2026, 3, 5, 9, 0);
        LocalDateTime end1 = LocalDateTime.of(2026, 3, 5, 12, 0);
        LocalDateTime start2 = LocalDateTime.of(2026, 3, 1, 9, 0); // Earliest
        LocalDateTime end2 = LocalDateTime.of(2026, 3, 1, 12, 0);
        LocalDateTime start3 = LocalDateTime.of(2026, 3, 3, 9, 0);
        LocalDateTime end3 = LocalDateTime.of(2026, 3, 3, 12, 0);

        Lesson lesson1 = new Lesson("Day 5", start1, end1);
        Lesson lesson2 = new Lesson("Day 1", start2, end2);
        Lesson lesson3 = new Lesson("Day 3", start3, end3);

        // Test with different list orders
        CourseRun courseRun1 = new CourseRun(UUID.randomUUID(), 10, List.of(lesson1, lesson2, lesson3), Collections.emptyList());
        CourseRun courseRun2 = new CourseRun(UUID.randomUUID(), 10, List.of(lesson3, lesson1, lesson2), Collections.emptyList());
        CourseRun courseRun3 = new CourseRun(UUID.randomUUID(), 10, List.of(lesson2, lesson3, lesson1), Collections.emptyList());

        assertEquals(start2, courseRun1.startTime());
        assertEquals(start2, courseRun2.startTime());
        assertEquals(start2, courseRun3.startTime());
    }
}
