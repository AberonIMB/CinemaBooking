package com.cinema.cinemabooking.exception.hall;

/**
 * Исключение, которое выбрасывается при попытке создать зал с уже существующим названием
 */
public class HallAlreadyExistsException extends HallException {

    public HallAlreadyExistsException(String name) {
        super("Зал с названием \"" + name + "\" уже существует");
    }
}