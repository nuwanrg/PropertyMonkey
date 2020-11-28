package com.pm.auth.exception;

public final class EmailAlreadyExistException extends RuntimeException {
    private static final long serialVersionUID = 1607059555479157116L;

    public EmailAlreadyExistException() {
        super();
    }

    public EmailAlreadyExistException(final String message) {
        super(message);
    }
}
