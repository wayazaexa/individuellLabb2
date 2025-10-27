package org.example.Exceptions;

public class MissingCandidateNameException extends RuntimeException {
    public MissingCandidateNameException(String message) {
        super(message);
    }
}
