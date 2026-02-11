package com.zuehlke.academy.academy.domain;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    // ID validation tests
    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(null, "Course Name", "Description", Collections.emptyList())
        );
        assertEquals("Course id must not be null", exception.getMessage());
    }

    // Name validation tests
    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), null, "Description", Collections.emptyList())
        );
        assertEquals("Course name must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), "", "Description", Collections.emptyList())
        );
        assertEquals("Course name must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), "   ", "Description", Collections.emptyList())
        );
        assertEquals("Course name must not be blank", exception.getMessage());
    }

    // Description validation tests
    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Course(UUID.randomUUID(), "Course Name", null, Collections.emptyList())
        );
        assertEquals("Course description must not be null", exception.getMessage());
    }

    // Valid course test
    @Test
    void shouldCreateCourseWithAllValidFields() {
        assertDoesNotThrow(
                () -> new Course(UUID.randomUUID(), "Java Programming", "Description", Collections.emptyList())
        );
    }

    @Test
    void shouldCreateCourseWithValidFieldsIncludingCourseRuns() {
        assertDoesNotThrow(
                () -> {
                    new Course(UUID.randomUUID(), "Java Programming", "Learn Java basics", Collections.emptyList());
                }
        );
    }
}
