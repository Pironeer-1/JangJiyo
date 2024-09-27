package com.example.myconvention.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private Exception originException;
    private final ErrorCode errorcode;

    public CustomException(ErrorCode errorcode) {
        this.errorcode = errorcode;
    }

    public CustomException(Exception originException, ErrorCode errorcode) {
        this.originException = originException;
        this.errorcode = errorcode;
    }
}
