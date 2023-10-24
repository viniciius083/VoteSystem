package com.vinicius.vs.exceptions.errors;

public class DataIntegratyViolationException extends RuntimeException{
    public DataIntegratyViolationException() {
    }

    public DataIntegratyViolationException(String message) {
        super(message);
    }

    public DataIntegratyViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
