package com.cinema.cinemabooking.exception.booking;

import com.cinema.cinemabooking.exception.ResourceNotFoundException;

/**
 * Исключение, которое выбрасывается, если бронь не найдена
 */
public class BookingNotFoundException extends ResourceNotFoundException {

    public BookingNotFoundException() {
        super("Бронь не найдена");
    }
}
