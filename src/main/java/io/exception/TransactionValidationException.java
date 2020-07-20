package io.exception;

public class TransactionValidationException extends ValidationException {
    public TransactionValidationException(String message) {
        super(message);
    }

    public TransactionValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
