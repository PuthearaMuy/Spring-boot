package com.springboot.theara.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
         return new ResponseEntity<Object>("This method not support in controller",HttpStatus.METHOD_NOT_ALLOWED);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException)
    {
        ErrorMessage error=new ErrorMessage(HttpStatus.NOT_FOUND.value(),notFoundException.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataAccessException.class)
    public ResponseEntity<Object> handleInvalidDataAccess(InvalidDataAccessException invalidDataAccessException)
    {
        ErrorMessage error=new ErrorMessage(HttpStatus.BAD_REQUEST.value(), invalidDataAccessException.getMessage());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<Object> handleDeleteException(DeleteException deleteException)
    {
        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.BAD_REQUEST.value(), deleteException.getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException constraintViolationException)
    {
        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Emai lId duplicated.");
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(JsonMappingException.class)
//    public ResponseEntity<Object> handleJsonException(JsonMappingException jsonMappingException)
//    {
//        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Correct your json body");
//        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
//    }

}
