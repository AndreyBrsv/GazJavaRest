package io.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DocumentDeleteException extends RuntimeException {
    public DocumentDeleteException(String message) {
        super(message);
    }

    public DocumentDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
