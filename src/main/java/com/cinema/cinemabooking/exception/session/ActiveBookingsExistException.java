package com.cinema.cinemabooking.exception.session;

import com.cinema.cinemabooking.model.Session;

/**
 * Исключение, которое выбрасывается при попытке изменить параметры сеанса с активными бронями
 */
public class ActiveBookingsExistException extends RuntimeException {

    public ActiveBookingsExistException(Session session) {
        super("Нельзя изменить параметры сеанса: \"" + session + "\", так как он имеет активные брони");
    }
}