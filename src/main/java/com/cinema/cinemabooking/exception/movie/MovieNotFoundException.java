package com.cinema.cinemabooking.exception.movie;

/**
 * Исключение, которое выбрасывается при попытке найти несуществующий фильм
 */
public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(Long id) {
        super("Фильм с id " + id + " не найден");
    }
}
