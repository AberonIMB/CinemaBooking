package com.cinema.cinemabooking.dto;

import com.cinema.cinemabooking.dto.movie.ScheduleMovieDTO;
import com.cinema.cinemabooking.dto.session.ScheduleSessionDTO;

import java.util.List;

/**
 * DTO для отображения расписания
 */
public class ScheduleDTO {

    private ScheduleMovieDTO movieDTO;
    private List<ScheduleSessionDTO> sessionDTO;

    public ScheduleDTO(ScheduleMovieDTO movieDTO, List<ScheduleSessionDTO> sessionDTO) {
        this.movieDTO = movieDTO;
        this.sessionDTO = sessionDTO;
    }

    public ScheduleDTO() {

    }

    public ScheduleMovieDTO getMovieDTO() {
        return movieDTO;
    }

    public void setMovieDTO(ScheduleMovieDTO movieDTO) {
        this.movieDTO = movieDTO;
    }

    public List<ScheduleSessionDTO> getSessionDTO() {
        return sessionDTO;
    }

    public void setSessionDTO(List<ScheduleSessionDTO> sessionDTO) {
        this.sessionDTO = sessionDTO;
    }
}