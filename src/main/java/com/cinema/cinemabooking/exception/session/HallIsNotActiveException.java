package com.cinema.cinemabooking.exception.session;

/**
 * Исключение, которое выбрасывается при попытке использовать неактивный зал
 */
public class HallIsNotActiveException extends SessionException {

    public HallIsNotActiveException(Long id) {
        super("Невозможно создать сеанс в этом зале с ID: " + id + ", так как он не активен");
    }
}
