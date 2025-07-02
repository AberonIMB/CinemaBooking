package com.cinema.cinemabooking.repository;

import com.cinema.cinemabooking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    /**
     * Получить список активных фильмов без активных сеансов
     * @return Список фильмов
     */
    @Query("SELECT m FROM Movie m WHERE m.isActive = true " +
            "AND NOT EXISTS (SELECT s FROM Session s WHERE s.movie = m AND s.isActive = true)")
    List<Movie> findActiveMoviesWithoutActiveSessions();
}
