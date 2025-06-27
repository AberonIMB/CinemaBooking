package com.cinema.cinemabooking.exception.hall;

/**
 * Исключение, которое выбрасывается при попытке найти несуществующий зал
 */
public class HallNotFoundException extends RuntimeException {

    public HallNotFoundException(Long id) {
        super("Зал с id " + id + " не найден");
    }
}
