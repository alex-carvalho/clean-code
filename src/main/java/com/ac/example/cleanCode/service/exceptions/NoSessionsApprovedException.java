package com.ac.example.cleanCode.service.exceptions;

/**
 * @author Alex Carvalho
 */
public class NoSessionsApprovedException extends RuntimeException {


    public NoSessionsApprovedException(String message) {
        super(message);
    }
}
