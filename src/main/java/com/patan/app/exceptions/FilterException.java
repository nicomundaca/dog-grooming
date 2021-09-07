package com.patan.app.exceptions;

public class FilterException extends Exception {

    public FilterException(String string) {
        super(string);
    }

    public FilterException(CommonException e) {
        super(e);
    }

    public FilterException(String message, Throwable cause) {
        super(message, cause);
    }
}
