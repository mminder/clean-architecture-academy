package com.zuehlke.academy.shared.validation;

import com.zuehlke.academy.shared.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    // notNull tests
    @Test
    void notNull_shouldReturnObjectWhenNotNull() {
        String result = Validation.notNull("test", "Should not throw");
        assertEquals("test", result);
    }

    @Test
    void notNull_shouldReturnObjectWhenNotNull_withComplexObject() {
        Integer value = 42;
        Integer result = Validation.notNull(value, "Should not throw");
        assertEquals(42, result);
        assertSame(value, result);
    }

    @Test
    void notNull_shouldThrowExceptionWhenNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> Validation.notNull(null, "Object cannot be null")
        );
        assertEquals("Object cannot be null", exception.getMessage());
    }

    @Test
    void notNull_shouldThrowExceptionWithCustomMessage() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> Validation.notNull(null, "Custom error message")
        );
        assertEquals("Custom error message", exception.getMessage());
    }

    // notBlank tests
    @Test
    void notBlank_shouldReturnStringWhenNotBlank() {
        String result = Validation.notBlank("test", "Should not throw");
        assertEquals("test", result);
    }

    @Test
    void notBlank_shouldReturnStringWithWhitespaceInMiddle() {
        String result = Validation.notBlank("hello world", "Should not throw");
        assertEquals("hello world", result);
    }

    @Test
    void notBlank_shouldReturnNonStringObjectWhenNotNull() {
        Integer value = 42;
        Integer result = Validation.notBlank(value, "Should not throw");
        assertEquals(42, result);
        assertSame(value, result);
    }

    @Test
    void notBlank_shouldThrowExceptionWhenNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> Validation.notBlank(null, "String cannot be null")
        );
        assertEquals("String cannot be null", exception.getMessage());
    }

    @Test
    void notBlank_shouldThrowExceptionWhenEmptyString() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> Validation.notBlank("", "String cannot be blank")
        );
        assertEquals("String cannot be blank", exception.getMessage());
    }

    @Test
    void notBlank_shouldThrowExceptionWhenOnlyWhitespace() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> Validation.notBlank("   ", "String cannot be blank")
        );
        assertEquals("String cannot be blank", exception.getMessage());
    }

    @Test
    void notBlank_shouldThrowExceptionWhenOnlyTabs() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> Validation.notBlank("\t\t", "String cannot be blank")
        );
        assertEquals("String cannot be blank", exception.getMessage());
    }

    @Test
    void notBlank_shouldThrowExceptionWhenMixedWhitespace() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> Validation.notBlank(" \t \n ", "String cannot be blank")
        );
        assertEquals("String cannot be blank", exception.getMessage());
    }

    // Edge cases and integration
    @Test
    void notNull_shouldWorkInMethodChaining() {
        String result = Validation.notNull(
                Validation.notNull("test", "Inner validation failed"),
                "Outer validation failed"
        );
        assertEquals("test", result);
    }

    @Test
    void notBlank_shouldWorkInMethodChaining() {
        String result = Validation.notBlank(
                Validation.notBlank("test", "Inner validation failed"),
                "Outer validation failed"
        );
        assertEquals("test", result);
    }
}
