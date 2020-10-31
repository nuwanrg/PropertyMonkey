package com.pm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHelper {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHelper.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> handleException(Exception e){
        logger.error(e.getMessage());
        return new ResponseEntity<Object>(new Error() , HttpStatus.BAD_REQUEST);
    }


}
