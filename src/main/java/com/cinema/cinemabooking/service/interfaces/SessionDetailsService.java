package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.dto.booking.BookingSessionDTO;
import com.cinema.cinemabooking.model.Session;

/**
 * Сервис для работы с сеансами и их бронированием
 */
public interface SessionDetailsService {

    /**
     * Получить данные для отображения сеанса и его бронирований
     * @param session сеанс
     * @return {@link BookingSessionDTO}
     */
    BookingSessionDTO getSessionDetails(Session session);
}
