package com.cinema.cinemabooking.mapper.interfaces;

import com.cinema.cinemabooking.dto.movie.AdminMovieDTO;
import com.cinema.cinemabooking.dto.movie.CreateEditMovieDTO;
import com.cinema.cinemabooking.dto.movie.ScheduleMovieDTO;
import com.cinema.cinemabooking.dto.movie.ShortMovieDTO;
import com.cinema.cinemabooking.model.Movie;

/**
 * Маппер для фильмов
 */
public interface MovieMapper {

    /**
     * Конвертирует фильм в AdminMovieDTO
     * @param movie фильм
     * @return AdminMovieDTO полная информация о фильме
     */
    AdminMovieDTO mapToAdminMovieDTO(Movie movie);

    /**
     * Конвертирует фильм в ShortMovieDTO
     * @param movie фильм
     * @return ShortMovieDTO краткая информация о фильме
     */
    ShortMovieDTO mapToShortMovieDTO(Movie movie);

    /**
     * Конвертирует фильм в EditMovieDTO
     * @param movie фильм
     * @return EditMovieDTO данные для редактирования фильма
     */
    CreateEditMovieDTO mapToCreateEditMovieDTO(Movie movie);

    ScheduleMovieDTO mapToScheduleMovieDTO(Movie movie);
}
