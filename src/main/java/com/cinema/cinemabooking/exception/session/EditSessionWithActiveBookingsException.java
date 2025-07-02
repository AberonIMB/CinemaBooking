package com.cinema.cinemabooking.exception.session;

import com.cinema.cinemabooking.model.Session;

/**
 * Исключение, которое выбрасывается при попытке изменить параметры сеанса с активными бронями
 */
public class EditSessionWithActiveBookingsException extends SessionException {

    public EditSessionWithActiveBookingsException(Session session) {
        super("Нельзя изменить сеанс: \"" + session + "\", так как он имеет активные брони");
    }
}