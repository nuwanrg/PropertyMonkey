
package com.pm;

import com.pm.auth.exception.EmailAlreadyExistException;
import com.pm.auth.exception.PasswordResetException;
import com.pm.auth.exception.UserAlreadyExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);


    // 409
    @ExceptionHandler({ UserAlreadyExistException.class })
    public ResponseEntity<Object> handleUserAlreadyExist(final RuntimeException ex, final WebRequest request) {
        //logger.error("409 Status Code", ex);
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.regError", null, request.getLocale()), "UserAlreadyExist");
        return handleExceptionInternal( ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    // 409
    @ExceptionHandler({ EmailAlreadyExistException.class })
    public ResponseEntity<Object> handleEmailAlreadyExist(final RuntimeException ex, final WebRequest request) {
        //logger.error("409 Status Code", ex);
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.regError", null, request.getLocale()), "UserAlreadyExist");
        return handleExceptionInternal( ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({ PasswordResetException.class })
    public ResponseEntity<Object> passwordResetEmailNotFound(final RuntimeException ex, final WebRequest request) {
        //logger.error("409 Status Code", ex);
        //final GenericResponse bodyOfResponse = new GenericResponse(messages.getMessage("message.regError", null, request.getLocale()), "UserAlreadyExist");
        return handleExceptionInternal( ex, ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}

