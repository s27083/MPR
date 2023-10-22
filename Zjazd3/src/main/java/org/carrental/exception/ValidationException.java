package org.carrental.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {
    private String field;
    private String message;

    public ValidationException(String message, String field) {
        super(message);
        this.field = field;
        this.message = message;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String getMessage() {
        return field + " " + message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
