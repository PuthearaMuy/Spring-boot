package com.springboot.theara.exceptions;

public class InvalidDataAccessException extends RuntimeException{
    public InvalidDataAccessException(String message)
    {
        super(message);
    }
}
