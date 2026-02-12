package com.zuehlke.academy.shared.exception;

public class ConsistencyException extends RuntimeException {
    public ConsistencyException(String message) {
        super(message);
    }
}
