package com.cinema.cinemabooking.exception.booking;

/**
 * Исключение, которое выбрасывается, если во время отмены брони произошла ошибка
 */
public class BookingCancellationException extends BookingException {

    public BookingCancellationException(String message) {
        super(message);
    }
}
