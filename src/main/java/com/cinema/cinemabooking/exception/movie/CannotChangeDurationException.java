package com.cinema.cinemabooking.exception.movie;

import com.cinema.cinemabooking.model.Movie;

/**
 * Исключение, которое выбрасывается при попытке изменить продолжительность фильма с активными сеансами
 */
public class CannotChangeDurationException extends RuntimeException {

    public CannotChangeDurationException(Movie movie) {
        super("Нельзя изменить продолжительность фильма " + movie.getTitle() + " , так как у него есть активные сеансы");
    }
}
