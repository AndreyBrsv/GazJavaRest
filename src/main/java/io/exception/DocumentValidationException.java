package io.exception;

public class DocumentValidationException extends ValidationException {
    public DocumentValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentValidationException(String message) {
        super(message);
    }
}
