package com.patan.app.exceptions;

public class CommonException extends java.lang.Exception {

    public CommonException(String string){
        super(string);
    }

    public CommonException(CommonException e){
        super(e);
    }

    public CommonException(String message, Throwable cause){
        super(message,cause);
    }
}
