package com.cinema.cinemabooking.exception.seat;

import com.cinema.cinemabooking.exception.ResourceNotFoundException;

/**
 * Исключение, которое выбрасывается при попытке найти несуществующее место
 */
public class SeatNotFoundException extends ResourceNotFoundException {

    public SeatNotFoundException(Long id) {
        super("Место с ID " + id + " не найдено");
    }
}
