package com.cinema.cinemabooking.exception.movie;

import com.cinema.cinemabooking.exception.ResourceNotFoundException;

/**
 * Исключение, которое выбрасывается при попытке найти несуществующий фильм
 */
public class MovieNotFoundException extends ResourceNotFoundException {

    public MovieNotFoundException(Long id) {
        super("Фильм с id " + id + " не найден");
    }
}
