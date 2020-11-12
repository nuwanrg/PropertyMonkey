package com.pm.auth.exception;

public final class PasswordResetException extends RuntimeException{
    private static final long serialVersionUID = 8641625342767347555L;

    public PasswordResetException(){
        super();
    }

    public PasswordResetException(final String message){
        super(message);
    }
}
