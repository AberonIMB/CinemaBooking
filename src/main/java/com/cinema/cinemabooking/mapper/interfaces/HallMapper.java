package com.cinema.cinemabooking.mapper.interfaces;

import com.cinema.cinemabooking.dto.hall.AdminHallDTO;
import com.cinema.cinemabooking.dto.hall.EditHallDTO;
import com.cinema.cinemabooking.dto.hall.ShortHallDTO;
import com.cinema.cinemabooking.model.Hall;

import java.util.List;

/**
 * Маппер для залов
 */
public interface HallMapper {

    /**
     * Конвертирует Зал в {@link ShortHallDTO}
     * @param hall зал
     * @return {@link ShortHallDTO}
     */
    ShortHallDTO mapToShortHallDTO(Hall hall);

    /**
     * Конвертирует Зал в {@link AdminHallDTO}
     * @param hall зал
     * @return {@link AdminHallDTO}
     */
    AdminHallDTO mapToAdminHallDTO(Hall hall);

    /**
     * Конвертирует Зал в {@link EditHallDTO}
     * @param hall зал
     * @return {@link EditHallDTO}
     */
    EditHallDTO mapToEditHallDTO(Hall hall);

    /**
     * Конвертирует список Залов в список {@link ShortHallDTO}
     * @param hallList список Залов
     * @return список {@link ShortHallDTO}
     */
    List<ShortHallDTO> mapToShortHallDTOList(List<Hall> hallList);

    /**
     * Конвертирует список Залов в список {@link AdminHallDTO}
     * @param hallList список Залов
     * @return список {@link AdminHallDTO}
     */
    List<AdminHallDTO> mapToAdminHallDTOList(List<Hall> hallList);
}
