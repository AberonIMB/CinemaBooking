package com.cinema.cinemabooking.exception.booking;

import com.cinema.cinemabooking.model.Booking;

public class BookingPaymentException extends RuntimeException {
    public BookingPaymentException(Booking booking) {
        super("Невозможно оплатить бронь: " + booking.getId() + ", так как она имеет статус " + booking.getStatus());
    }
}
