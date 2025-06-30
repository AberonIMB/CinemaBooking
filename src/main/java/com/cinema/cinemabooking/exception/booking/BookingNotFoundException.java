package com.cinema.cinemabooking.exception.booking;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException() {
        super("Бронь не найдена");
    }
}
