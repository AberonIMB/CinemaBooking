package com.cinema.cinemabooking.exception.session;

import com.cinema.cinemabooking.model.Session;

public class SessionAlreadyFinishedException extends RuntimeException {

    public SessionAlreadyFinishedException(Session session) {
        super("Сеанс с ID " + session.getId() + " уже завершен. Время сеанса: " + session.getStartTime());
    }
}
