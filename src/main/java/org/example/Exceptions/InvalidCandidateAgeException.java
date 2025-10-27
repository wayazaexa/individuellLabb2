package org.example.Exceptions;

public class InvalidCandidateAgeException extends RuntimeException {
    public InvalidCandidateAgeException(String message) {
        super(message);
    }
}
