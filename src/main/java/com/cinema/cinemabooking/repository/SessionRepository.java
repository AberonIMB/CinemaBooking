package com.cinema.cinemabooking.repository;

import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Репозиторий для сеансов
 */
public interface SessionRepository extends JpaRepository<Session, Long> {

    /**
     * Получить все активные сеансы для конкретного зала
     * @param hall зал
     */
    List<Session> getSessionsByIsActiveTrueAndHall(Hall hall);

    /**
     * Получить все сеансы для конкретного зала в определенный день, включая пересекающиеся сеансы
     * Учитываются сеансы, у которых начало до конца дня и конец до после начала дня
     * @param hall зал
     * @param startOfDay начало дня
     * @param endOfDay конец дня
     */
    @Query("SELECT s FROM Session s WHERE s.hall = :hall " +
            "AND s.startTime < :endOfDay " +
            "AND s.endTime > :startOfDay " +
            " AND s.isActive = true")
    List<Session> findActiveSessionsByHallIdAndDateOverlap(@Param("hall") Hall hall,
                                                           @Param("startOfDay") LocalDateTime startOfDay,
                                                           @Param("endOfDay") LocalDateTime endOfDay);


    /**
     * Поиск сеансов по фильтрам
     * @param movieTitle часть названия фильма
     * @param hallId идентификатор зала
     * @param date дата
     */
    @Query("SELECT s FROM Session s " +
            "WHERE (:movieTitle IS NULL OR LOWER(s.movie.title) LIKE LOWER(CONCAT('%', :movieTitle, '%'))) " +
            "AND (:hallId IS NULL OR s.hall.id = :hallId) " +
            "AND (:date IS NULL OR FUNCTION('DATE', s.startTime) = :date)")
    List<Session> findSessionsByFilters(@Param("movieTitle") String movieTitle,
                                         @Param("hallId") Long hallId,
                                         @Param("date") LocalDate date);

    @Query("SELECT s FROM Session s " +
            "WHERE s.isActive = true " +
            "AND FUNCTION('DATE', s.startTime) = :date")
    List<Session> findActiveSessionsByDate(@Param("date") LocalDate date);

    @Query("SELECT s FROM Session s " +
            "WHERE s.movie = :movie " +
            "AND s.isActive = true " +
            "AND FUNCTION('DATE', s.startTime) = :date")
    List<Session> findActiveSessionsByMovieAndDate(@Param("movie") Movie movie,
                                                   @Param("date") LocalDate date);

    List<Session> findByIsActive(boolean isActive);
}