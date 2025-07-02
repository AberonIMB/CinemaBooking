package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.dto.hall.CreateHallDTO;
import com.cinema.cinemabooking.dto.hall.EditHallDTO;
import com.cinema.cinemabooking.model.Hall;

import java.util.List;

/**
 * Сервис для работы с залами
 */
public interface HallService {

    /**
     * Получить список залов по статусу
     * @param isActive статус
     */
    List<Hall> getHallsByActive(boolean isActive);

    /**
     * Создание зала
     * @param createHallDTO данные для создания зала
     */
    void createHallFromDTO(CreateHallDTO createHallDTO);

    /**
     * Получить зал по идентификатору
     */
    Hall getHallById(Long id);

    /**
     * Обновление зала
     * @param id идентификатор зала
     * @param hallDTO данные для обновления
     */
    void updateHall(Long id, EditHallDTO hallDTO);

    /**
     * Перевести зал в статус неактивный
     * @param id идентификатор зала
     */
    void deactivateHall(Long id);

    /**
     * Перевести зал в статус активный
     * @param id идентификатор зала
     */
    void activateHall(Long id);

    /**
     * Получить список залов
     */
    List<Hall> getHalls();
}
