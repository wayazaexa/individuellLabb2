package org.example.Exceptions;

public class MissingCandidateTradeException extends RuntimeException {
    public MissingCandidateTradeException(String message) {
        super(message);
    }
}
