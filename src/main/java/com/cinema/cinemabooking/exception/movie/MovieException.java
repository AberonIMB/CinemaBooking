package com.cinema.cinemabooking.exception.movie;

public abstract class MovieException extends RuntimeException {

    public MovieException(String message) {
        super(message);
    }
}
