package com.cinema.cinemabooking.exception.session;

import com.cinema.cinemabooking.exception.ResourceNotFoundException;

/**
 * Исключение, которое выбрасывается при попытке найти несуществующий сеанс
 */
public class SessionNotFoundException extends ResourceNotFoundException {

    public SessionNotFoundException(Long id) {
        super("Сеанс с ID " + id + " не существует");
    }
}
