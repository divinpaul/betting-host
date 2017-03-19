package com.tabcorp.betting.exceptions;

public class BettingHostException extends RuntimeException {

    public BettingHostException(String message) {
        super(message);
    }

    public BettingHostException(String message, Throwable cause) {
        super(message, cause);
    }
}
