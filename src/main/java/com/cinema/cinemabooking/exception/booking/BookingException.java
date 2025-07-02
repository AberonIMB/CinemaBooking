package com.cinema.cinemabooking.exception.booking;

public abstract class BookingException extends RuntimeException {

    public BookingException(String message) {
        super(message);
    }
}
