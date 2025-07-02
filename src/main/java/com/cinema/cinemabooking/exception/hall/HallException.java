package com.cinema.cinemabooking.exception.hall;

public abstract class HallException extends RuntimeException {

    public HallException(String message) {
        super(message);
    }
}
