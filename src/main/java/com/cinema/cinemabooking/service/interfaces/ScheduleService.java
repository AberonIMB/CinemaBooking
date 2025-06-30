package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.dto.ScheduleDTO;
import com.cinema.cinemabooking.dto.session.ScheduleSessionDTO;
import com.cinema.cinemabooking.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    List<ScheduleDTO> getScheduleByDate(LocalDate date);
    List<ScheduleSessionDTO> getScheduleByMovie(Movie movie, LocalDate date);
}
