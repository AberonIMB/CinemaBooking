package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.dto.movie.CreateEditMovieDTO;
import com.cinema.cinemabooking.model.Movie;

import java.util.List;

/**
 * Сервис для работы с фильмами
 */
public interface MovieService {

    /**
     * Получить список фильмов по статусу
     * @param isActive статус
     */
    List<Movie> getMoviesByActive(boolean isActive);

    /**
     * Создание фильма
     * @param createEditMovieDTO данные для создания фильма
     */
    void createMovieFromDTO(CreateEditMovieDTO createEditMovieDTO);

    /**
     * Получить фильм по идентификатору
     * @param id идентификатор
     */
    Movie getMovieById(Long id);

    /**
     * Обновление фильма
     * @param id идентификатор фильма
     * @param movieDTO данные для обновления фильма
     */
    void updateMovie(Long id, CreateEditMovieDTO movieDTO);

    /**
     * Получить список фильмов по названию
     * @param query запрос
     * @return
     */
    List<Movie> searchMoviesByTitle(String query);

    /**
     * Перевести фильм в активный
     * @param movie фильм
     */
    void activateMovie(Movie movie);

    /**
     * Перевести фильм в неактивный
     * @param movie фильм
     */
    void deactivateMovie(Movie movie);

    List<Movie> getActiveMoviesWithoutActiveSessions();

    void saveAll(List<Movie> movies);
}