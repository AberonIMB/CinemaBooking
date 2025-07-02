package com.cinema.cinemabooking.mapper.interfaces;

import com.cinema.cinemabooking.dto.movie.AdminMovieDTO;
import com.cinema.cinemabooking.dto.movie.CreateEditMovieDTO;
import com.cinema.cinemabooking.dto.movie.ScheduleMovieDTO;
import com.cinema.cinemabooking.dto.movie.ShortMovieDTO;
import com.cinema.cinemabooking.model.Movie;

import java.util.List;

/**
 * Маппер для фильмов
 */
public interface MovieMapper {

    /**
     * Конвертирует фильм в {@link AdminMovieDTO}
     * @param movie фильм
     * @return {@link AdminMovieDTO}
     */
    AdminMovieDTO mapToAdminMovieDTO(Movie movie);

    /**
     * Конвертирует фильм в {@link ShortMovieDTO}
     * @param movie фильм
     * @return {@link ShortMovieDTO}
     */
    ShortMovieDTO mapToShortMovieDTO(Movie movie);

    /**
     * Конвертирует фильм в {@link CreateEditMovieDTO}
     * @param movie фильм
     * @return {@link CreateEditMovieDTO}
     */
    CreateEditMovieDTO mapToCreateEditMovieDTO(Movie movie);

    /**
     * Конвертирует фильм в {@link ScheduleMovieDTO}
     * @param movie фильм
     * @return {@link ScheduleMovieDTO@}
     */
    ScheduleMovieDTO mapToScheduleMovieDTO(Movie movie);

    /**
     * Конвертирует список фильмов в список {@link AdminMovieDTO}
     * @param movieList список фильмов
     * @return список {@link AdminMovieDTO}
     */
    List<AdminMovieDTO> mapToAdminMovieDTOList(List<Movie> movieList);

    /**
     * Конвертирует список фильмов в список {@link ShortMovieDTO}
     * @param movieList список фильмов
     * @return список {@link ShortMovieDTO}
     */
    List<ShortMovieDTO> mapToShortMovieDTOList(List<Movie> movieList);
}
