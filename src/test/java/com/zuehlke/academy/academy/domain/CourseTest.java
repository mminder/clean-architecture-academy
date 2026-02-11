package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    // ID validation tests
    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(null, "Course Name", "Description", new ArrayList<>())
        );
        assertEquals("Course id must not be null", exception.getMessage());
    }

    // Name validation tests
    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), null, "Description", new ArrayList<>())
        );
        assertEquals("Course name must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), "", "Description", new ArrayList<>())
        );
        assertEquals("Course name must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), "   ", "Description", new ArrayList<>())
        );
        assertEquals("Course name must not be blank", exception.getMessage());
    }

    // Description validation tests
    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), "Course Name", null, new ArrayList<>())
        );
        assertEquals("Course description must not be null", exception.getMessage());
    }

    // CourseRuns validation tests
    @Test
    void shouldThrowExceptionWhenCourseRunsIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), "Course Name", "Description", null)
        );
        assertEquals("Course courseRuns must not be null", exception.getMessage());
    }

    // Valid course test
    @Test
    void shouldCreateCourseWithAllValidFields() {
        assertDoesNotThrow(
                () -> new Course(UUID.randomUUID(), "Java Programming", "Description", new ArrayList<>())
        );
    }

    @Test
    void shouldCreateCourseWithValidFieldsIncludingCourseRuns() {
        assertDoesNotThrow(
                () -> {
                    var courseRuns = new ArrayList<CourseRun>();
                    courseRuns.add(new CourseRun(20, Collections.emptyList()));
                    new Course(UUID.randomUUID(), "Java Programming", "Learn Java basics", courseRuns);
                }
        );
    }

    // nextCourseRunStartTime() method tests
    @Test
    void shouldReturnNullWhenNoCourseRuns() {
        Course course = new Course(UUID.randomUUID(), "Java", "Description", List.of());

        assertNull(course.nextCourseRunStartTime());
    }

    @Test
    void shouldReturnNullWhenCourseRunsHaveNoLessons() {
        CourseRun courseRun1 = new CourseRun(10, List.of());
        CourseRun courseRun2 = new CourseRun(15, List.of());
        Course course = new Course(UUID.randomUUID(), "Java", "Description", List.of(courseRun1, courseRun2));

        assertNull(course.nextCourseRunStartTime());
    }

    @Test
    void shouldReturnStartTimeOfSingleCourseRun() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 17, 0);
        Lesson lesson = new Lesson("Java Basics", start, end);
        CourseRun courseRun = new CourseRun(10, List.of(lesson));
        Course course = new Course(UUID.randomUUID(), "Java", "Description", List.of(courseRun));

        assertEquals(start, course.nextCourseRunStartTime());
    }

    @Test
    void shouldReturnEarliestCourseRunStartTime() {
        // Create lessons for different course runs
        LocalDateTime start1 = LocalDateTime.of(2026, 4, 1, 9, 0);
        LocalDateTime end1 = LocalDateTime.of(2026, 4, 1, 17, 0);
        Lesson lesson1 = new Lesson("Session 1", start1, end1);

        LocalDateTime start2 = LocalDateTime.of(2026, 3, 1, 9, 0); // Earliest
        LocalDateTime end2 = LocalDateTime.of(2026, 3, 1, 17, 0);
        Lesson lesson2 = new Lesson("Session 2", start2, end2);

        LocalDateTime start3 = LocalDateTime.of(2026, 5, 1, 9, 0);
        LocalDateTime end3 = LocalDateTime.of(2026, 5, 1, 17, 0);
        Lesson lesson3 = new Lesson("Session 3", start3, end3);

        CourseRun courseRun1 = new CourseRun(10, List.of(lesson1));
        CourseRun courseRun2 = new CourseRun(15, List.of(lesson2));
        CourseRun courseRun3 = new CourseRun(20, List.of(lesson3));

        Course course = new Course(UUID.randomUUID(), "Java", "Description",
                List.of(courseRun1, courseRun2, courseRun3));

        assertEquals(start2, course.nextCourseRunStartTime());
    }

    @Test
    void shouldReturnEarliestCourseRunRegardlessOfListOrder() {
        LocalDateTime start1 = LocalDateTime.of(2026, 5, 1, 9, 0);
        LocalDateTime end1 = LocalDateTime.of(2026, 5, 1, 17, 0);
        Lesson lesson1 = new Lesson("May Session", start1, end1);

        LocalDateTime start2 = LocalDateTime.of(2026, 3, 1, 9, 0); // Earliest
        LocalDateTime end2 = LocalDateTime.of(2026, 3, 1, 17, 0);
        Lesson lesson2 = new Lesson("March Session", start2, end2);

        LocalDateTime start3 = LocalDateTime.of(2026, 4, 1, 9, 0);
        LocalDateTime end3 = LocalDateTime.of(2026, 4, 1, 17, 0);
        Lesson lesson3 = new Lesson("April Session", start3, end3);

        CourseRun courseRun1 = new CourseRun(10, List.of(lesson1));
        CourseRun courseRun2 = new CourseRun(15, List.of(lesson2));
        CourseRun courseRun3 = new CourseRun(20, List.of(lesson3));

        // Test with different orders
        Course course1 = new Course(UUID.randomUUID(), "Java", "Description",
                List.of(courseRun1, courseRun2, courseRun3));
        Course course2 = new Course(UUID.randomUUID(), "Java", "Description",
                List.of(courseRun3, courseRun1, courseRun2));
        Course course3 = new Course(UUID.randomUUID(), "Java", "Description",
                List.of(courseRun2, courseRun3, courseRun1));

        assertEquals(start2, course1.nextCourseRunStartTime());
        assertEquals(start2, course2.nextCourseRunStartTime());
        assertEquals(start2, course3.nextCourseRunStartTime());
    }

    @Test
    void shouldHandleMixOfCourseRunsWithAndWithoutLessons() {
        LocalDateTime start = LocalDateTime.of(2026, 3, 1, 9, 0);
        LocalDateTime end = LocalDateTime.of(2026, 3, 1, 17, 0);
        Lesson lesson = new Lesson("Session", start, end);

        CourseRun courseRunWithLesson = new CourseRun(10, List.of(lesson));
        CourseRun courseRunWithoutLessons1 = new CourseRun(15, List.of());
        CourseRun courseRunWithoutLessons2 = new CourseRun(20, List.of());

        Course course = new Course(UUID.randomUUID(), "Java", "Description",
                List.of(courseRunWithoutLessons1, courseRunWithLesson, courseRunWithoutLessons2));

        assertEquals(start, course.nextCourseRunStartTime());
    }
}
