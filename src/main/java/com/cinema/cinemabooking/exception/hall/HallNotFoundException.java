package com.cinema.cinemabooking.exception.hall;

import com.cinema.cinemabooking.exception.ResourceNotFoundException;

/**
 * Исключение, которое выбрасывается при попытке найти несуществующий зал
 */
public class HallNotFoundException extends ResourceNotFoundException {

    public HallNotFoundException(Long id) {
        super("Зал с id " + id + " не найден");
    }
}
