package com.cinema.cinemabooking.repository;

import com.cinema.cinemabooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для фильмов
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

    /**
     * Получить список фильмов по статусу
     * @param isActive статус
     */
    List<Movie> findByIsActive(boolean isActive);

    /**
     * Получить фильм по названию
     * @param title название
     */
    Movie findByTitle(String title);

    /**
     * Получить список фильмов по части названия
     * @param title часть названия
     */
    List<Movie> findByTitleContainingIgnoreCase(String title);
}
