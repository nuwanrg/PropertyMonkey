package com.pm.auth.exception;

public class InvalidOldPasswordException extends RuntimeException {
    public InvalidOldPasswordException(){
        super();
    }

    public InvalidOldPasswordException(final String message){
        super(message);
    }
}
