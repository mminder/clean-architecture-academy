package com.zuehlke.academy.academy.shared.validation;

import com.zuehlke.academy.academy.shared.exception.ApplicationException;

public class Validation {

    public static <T> T notNull(T object, String message) {
        if (object == null) {
            throw new ApplicationException(message);
        }
        return object;
    }

    public static <T> T notBlank(T object, String message) {
        if (object == null) {
            throw new ApplicationException(message);
        }
        if (object instanceof String && ((String) object).isBlank()) {
            throw new ApplicationException(message);
        }
        return object;
    }
}
