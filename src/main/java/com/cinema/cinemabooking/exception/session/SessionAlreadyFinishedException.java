package com.cinema.cinemabooking.exception.session;

import com.cinema.cinemabooking.exception.ResourceNotFoundException;
import com.cinema.cinemabooking.model.Session;

/**
 * Исключение, которое выбрасывается при попытке получить уже завершенный сеанс
 */
public class SessionAlreadyFinishedException extends ResourceNotFoundException {

    public SessionAlreadyFinishedException(Session session) {
        super("Сеанс с ID " + session.getId() + " уже завершен. Дата сеанса: " + session.getStartTime());
    }
}
