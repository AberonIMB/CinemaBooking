package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.dto.ScheduleDTO;
import com.cinema.cinemabooking.dto.session.ScheduleSessionDTO;
import com.cinema.cinemabooking.model.Movie;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис для работы с расписанием
 */
public interface ScheduleService {

    /**
     * Получить расписание по дате
     * @param date дата
     * @return список {@link ScheduleDTO}
     */
    List<ScheduleDTO> getScheduleByDate(LocalDate date);

    /**
     * Получить расписание для конкретного фильма в определенную дату
     * @param movie фильм
     * @param date дата
     * @return список {@link ScheduleSessionDTO}
     */
    List<ScheduleSessionDTO> getScheduleByMovie(Movie movie, LocalDate date);
}
