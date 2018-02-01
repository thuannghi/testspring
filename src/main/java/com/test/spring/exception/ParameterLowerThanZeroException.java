package com.test.spring.exception;

public class ParameterLowerThanZeroException extends BaseException {
    public ParameterLowerThanZeroException(String message) {
        super(message);
    }

    public ParameterLowerThanZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
