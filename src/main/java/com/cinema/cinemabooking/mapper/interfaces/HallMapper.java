package com.cinema.cinemabooking.mapper.interfaces;

import com.cinema.cinemabooking.dto.hall.AdminHallDTO;
import com.cinema.cinemabooking.dto.hall.EditHallDTO;
import com.cinema.cinemabooking.dto.hall.ShortHallDTO;
import com.cinema.cinemabooking.model.Hall;

/**
 * Маппер для залов
 */
public interface HallMapper {

    /**
     * Конвертирует Зал в ShortHallDTO
     * @param hall зал
     * @return ShortHallDTO краткая информация о зале
     */
    ShortHallDTO mapToShortHallDTO(Hall hall);

    /**
     * Конвертирует Зал в AdminHallDTO
     * @param hall зал
     * @return AdminHallDTO полная информация о зале
     */
    AdminHallDTO mapToAdminHallDTO(Hall hall);

    /**
     * Конвертирует Зал в EditHallDTO
     * @param hall зал
     * @return EditHallDTO данные для редактирования зала
     */
    EditHallDTO mapToEditHallDTO(Hall hall);
}
