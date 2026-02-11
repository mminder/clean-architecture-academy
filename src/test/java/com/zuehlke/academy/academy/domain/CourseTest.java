package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
                    courseRuns.add(new CourseRun(20));
                    new Course(UUID.randomUUID(), "Java Programming", "Learn Java basics", courseRuns);
                }
        );
    }
}
