package com.cinema.cinemabooking.exception.session;

public abstract class SessionException extends RuntimeException {

    public SessionException(String message) {
        super(message);
    }
}