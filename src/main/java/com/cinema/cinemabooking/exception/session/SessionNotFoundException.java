package com.cinema.cinemabooking.exception.session;

/**
 * Исключение, которое выбрасывается при попытке найти несуществующий сеанс
 */
public class SessionNotFoundException extends RuntimeException {

    public SessionNotFoundException(Long id) {
        super("Сеанс с ID " + id + " не найден");
    }
}
