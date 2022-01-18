package com.springboot.theara.exceptions;

public class DeleteException extends RuntimeException {
    public DeleteException(String message)
    {
        super(message);
    }
}
