package com.cinema.cinemabooking.exception.movie;

/**
 * Исключение, которое выбрасывается при попытке создать фильм с уже существующим названием
 */
public class MovieAlreadyExistsException extends MovieException {

    public MovieAlreadyExistsException(String title) {
        super("Фильм с названием " + title + " уже существует");
    }

}
