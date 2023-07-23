package com.example.restapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoBookFoundException.class)

    public ResponseEntity<ExceptionDetails> bookNotFoundException(NoBookFoundException noBookFoundException){
        ExceptionDetails exceptionDetails = new ExceptionDetails(0, noBookFoundException.getMessage());

        return new ResponseEntity<ExceptionDetails>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<?> globalExceptionHandler(Exception exception){

        ExceptionDetails exceptionDetails = new ExceptionDetails(0, exception.getMessage());

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
