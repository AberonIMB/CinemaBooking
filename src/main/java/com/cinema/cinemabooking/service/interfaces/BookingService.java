package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.model.Session;

/**
 * Сервис броней
 */
public interface BookingService {

    /**
     * Проверяет наличие активных броней в указанном сеансе
     * @param session сеанс
     */
    boolean hasSessionActiveBooking(Session session);
}
