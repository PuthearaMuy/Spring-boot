package com.springboot.theara.exceptions;

import lombok.Getter;

@Getter
public class ErrorMessage {
    private int code;
    private String massage;

    public ErrorMessage(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
