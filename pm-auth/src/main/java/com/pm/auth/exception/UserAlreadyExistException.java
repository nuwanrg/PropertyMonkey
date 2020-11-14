package com.pm.auth.exception;

public final class UserAlreadyExistException extends RuntimeException{
    private static final long serialVersionUID = -1176662508596979737L;

    public UserAlreadyExistException(final String message){
        super(message);
    }
}
