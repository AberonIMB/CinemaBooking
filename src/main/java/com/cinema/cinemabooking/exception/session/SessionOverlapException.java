package com.cinema.cinemabooking.exception.session;

import com.cinema.cinemabooking.model.Session;

/**
 * Исключение, которое выбрасывается при попытке создать сеанс, пересекающий уже существующий сеанс
 */
public class SessionOverlapException extends SessionException {

    public SessionOverlapException(Session session) {
        super("Данный сеанс пересекается с существующим сеансом: " + session);
    }
}