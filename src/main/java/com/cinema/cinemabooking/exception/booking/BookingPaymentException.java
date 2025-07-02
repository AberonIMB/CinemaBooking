package com.cinema.cinemabooking.exception.booking;

import com.cinema.cinemabooking.model.Booking;

/**
 * Исключение, которое выбрасывается, если во время оплаты брони произошла ошибка
 */
public class BookingPaymentException extends BookingException {
    public BookingPaymentException(Booking booking) {
        super("Невозможно оплатить бронь: " + booking.getId() + ", так как она имеет статус " + booking.getStatus());
    }
}
