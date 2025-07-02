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

    /**
     * Получить активные сеансы для конкретного дня
     * @param date дата
     * @return список сеансов
     */
    List<Session> getActiveSessionsByDate(LocalDate date);

    /**
     * Получить активные сеансы для конкретного фильма в определенную дату
     * @param movie фильм
     * @param date дата
     * @return список сеансов
     */
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

    /**
     * Получить список сеансов по статусу
     * @param status статус
     * @return список сеансов
     */
    List<Session> getSessionsByStatus(boolean status);

    /**
     * Обновить статус сеанса
     * @param session сеанс
     * @param status статус
     */
    void updateSessionStatus(Session session, boolean status);

    /**
     * Отменить сеанс
     * @param session сеанс
     */
    void cancelSession(Session session);
}
