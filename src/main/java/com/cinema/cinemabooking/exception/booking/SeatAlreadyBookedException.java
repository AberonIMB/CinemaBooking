package com.cinema.cinemabooking.exception.booking;

import com.cinema.cinemabooking.model.Seat;

public class SeatAlreadyBookedException extends RuntimeException {

    public SeatAlreadyBookedException(Seat seat) {
        super("Невозможно забронировать место: Ряд " +
                seat.getSeatRow() + ", Место " + seat.getSeatNumber() + ", так как оно уже забронировано");
    }
}