package com.zuehlke.academy.academy.domain.shared;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    // Valid email tests
    @Test
    void shouldCreateEmailWithValidAddress() {
        assertDoesNotThrow(() -> new Email("user@example.com"));
    }

    @Test
    void shouldCreateEmailWithSubdomain() {
        assertDoesNotThrow(() -> new Email("user@mail.example.com"));
    }

    @Test
    void shouldCreateEmailWithPlusSign() {
        assertDoesNotThrow(() -> new Email("user+tag@example.com"));
    }

    @Test
    void shouldCreateEmailWithDot() {
        assertDoesNotThrow(() -> new Email("first.last@example.com"));
    }

    @Test
    void shouldCreateEmailWithUnderscore() {
        assertDoesNotThrow(() -> new Email("user_name@example.com"));
    }

    @Test
    void shouldCreateEmailWithHyphen() {
        assertDoesNotThrow(() -> new Email("user-name@example.com"));
    }

    @Test
    void shouldCreateEmailWithNumbers() {
        assertDoesNotThrow(() -> new Email("user123@example456.com"));
    }

    @Test
    void shouldCreateEmailWithMultipleSubdomains() {
        assertDoesNotThrow(() -> new Email("user@mail.company.example.com"));
    }

    @Test
    void shouldCreateEmailWithLongTLD() {
        assertDoesNotThrow(() -> new Email("user@example.museum"));
    }

    // Null and blank validation tests
    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email(null)
        );
        assertEquals("Email must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsEmpty() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("")
        );
        assertEquals("Email must not be blank", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailIsBlank() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("   ")
        );
        assertEquals("Email must not be blank", exception.getMessage());
    }

    // Invalid format tests
    @Test
    void shouldThrowExceptionWhenEmailHasNoAtSign() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("userexample.com")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailHasMultipleAtSigns() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("user@@example.com")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailHasNoDomain() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("user@")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailHasNoUsername() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("@example.com")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailHasNoTLD() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("user@example")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTLDIsTooShort() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("user@example.c")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailHasSpaces() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("user name@example.com")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailHasInvalidCharacters() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("user#name@example.com")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailStartsWithDot() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email(".user@example.com")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEmailEndsWithDot() {
        ApplicationException exception = assertThrows(
                ApplicationException.class,
                () -> new Email("user.@example.com")
        );
        assertEquals("Email must be a valid email address", exception.getMessage());
    }

    // toString() tests
    @Test
    void shouldReturnEmailStringWhenToStringIsCalled() {
        Email email = new Email("user@example.com");
        assertEquals("user@example.com", email.toString());
    }

    @Test
    void shouldReturnSameStringAsValue() {
        Email email = new Email("test@domain.com");
        assertEquals(email.value(), email.toString());
    }

    // Equality tests (records automatically implement equals/hashCode)
    @Test
    void shouldBeEqualWhenEmailsHaveSameValue() {
        Email email1 = new Email("user@example.com");
        Email email2 = new Email("user@example.com");
        assertEquals(email1, email2);
    }

    @Test
    void shouldNotBeEqualWhenEmailsHaveDifferentValues() {
        Email email1 = new Email("user1@example.com");
        Email email2 = new Email("user2@example.com");
        assertNotEquals(email1, email2);
    }

    @Test
    void shouldHaveSameHashCodeWhenEmailsAreEqual() {
        Email email1 = new Email("user@example.com");
        Email email2 = new Email("user@example.com");
        assertEquals(email1.hashCode(), email2.hashCode());
    }

    // Value accessor test
    @Test
    void shouldReturnEmailValueThroughValueMethod() {
        Email email = new Email("user@example.com");
        assertEquals("user@example.com", email.value());
    }
}
