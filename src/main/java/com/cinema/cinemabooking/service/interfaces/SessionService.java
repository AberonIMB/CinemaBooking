package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.dto.session.SessionCreateData;
import com.cinema.cinemabooking.dto.session.SessionUpdateData;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.model.Session;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с сеансами
 */
public interface SessionService {

    /**
     * Получить активные сеансы для конкретного зала
     * @param hall зал
     */
    List<Session> getActiveSessionsByHall(Hall hall);

    List<Session> getActiveSessionsByDate(LocalDate date);

    List<Session> getActiveSessionsByMovieAndDate(Movie movie, LocalDate date);

    /**
     * Создание сеанса
     * @param session данные для создания сеанса
     */
    void createSession(SessionCreateData session);

    /**
     * Получить список сеансов по фильтрам
     * @param movieTitle название фильма
     * @param hallId идентификатор зала
     * @param date дата
     */
    List<Session> findSessionsByFilters(String movieTitle, Long hallId, LocalDate date);

    /**
     * Получить сеанс по идентификатору
     * @param id идентификатор
     */
    Session getSessionById(Long id);

    /**
     * Обновление сеанса
     * @param data данные для обновления сеанса
     */
    void updateSession(SessionUpdateData data);

    List<Session> getSessionsByStatus(boolean status);

    void updateSessionStatus(Session session, boolean status);
}
