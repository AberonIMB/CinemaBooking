package com.cinema.cinemabooking.exception.seat;

public class SeatNotFoundException extends RuntimeException {

    public SeatNotFoundException(Long id) {
        super("Место с ID " + id + " не найдено");
    }
}
