package com.ac.example.cleanCode.service.exceptions;

/**
 * @author Alex Carvalho
 */
public class CandidateDoesntMeetRequirementsException extends RuntimeException {


    public CandidateDoesntMeetRequirementsException(String message) {
        super(message);
    }
}
