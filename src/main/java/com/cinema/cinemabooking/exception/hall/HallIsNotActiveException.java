package com.cinema.cinemabooking.exception.hall;

/**
 * Исключение, которое выбрасывается при попытке использовать неактивный зал
 */
public class HallIsNotActiveException extends RuntimeException {

    public HallIsNotActiveException(Long id) {
        super("Зал с ID: " + id + " не активен");
    }
}
