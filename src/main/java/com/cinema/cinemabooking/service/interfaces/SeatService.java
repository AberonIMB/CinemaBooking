package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.model.Hall;


/**
 * Сервис для работы с местами
 */
public interface SeatService {

    /**
     * Создание мест для зала
     * @param hall зал
     */
    void createSeatsForHall(Hall hall);

    /**
     * Обновление мест для зала
     * @param hall зал
     */
    void updateSeatsForHall(Hall hall);
}
