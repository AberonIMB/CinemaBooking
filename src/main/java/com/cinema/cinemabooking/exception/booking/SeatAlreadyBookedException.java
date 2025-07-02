package com.cinema.cinemabooking.exception.booking;

import com.cinema.cinemabooking.model.Booking;

/**
 * Исключение, которое выбрасывается, если место уже забронировано
 */
public class SeatAlreadyBookedException extends RuntimeException {

    /**
     * Идентификатор сеанса, которому принадлежит забронированное место
     */
    private final Long sessionId;

    public SeatAlreadyBookedException(Booking booking) {
        super("Невозможно забронировать место: Ряд " +
                booking.getSeat().getSeatRow() +
                ", Место " +
                booking.getSeat().getSeatNumber() +
                ", так как оно уже забронировано");

        sessionId = booking.getSession().getId();

    }

    public Long getSessionId() {
        return sessionId;
    }
}