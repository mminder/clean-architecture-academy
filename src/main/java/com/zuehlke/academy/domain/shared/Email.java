package com.zuehlke.academy.domain.shared;

import com.zuehlke.academy.shared.exception.ApplicationException;

import java.util.regex.Pattern;

public record Email(String value) {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_-]+([.][A-Za-z0-9+_-]+)*@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public Email {
        if (value == null || value.isBlank()) {
            throw new ApplicationException("Email must not be blank");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new ApplicationException("Email must be a valid email address");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
