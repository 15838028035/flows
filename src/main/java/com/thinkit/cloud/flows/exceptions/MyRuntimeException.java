package com.thinkit.cloud.flows.exceptions;
public class MyRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -6925278824391495117L;

    public MyRuntimeException(String message) {
        super(message);
    }

}