package com.cinema.cinemabooking.mapper.impl;

import com.cinema.cinemabooking.dto.session.*;
import com.cinema.cinemabooking.mapper.interfaces.SessionMapper;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.service.interfaces.HallService;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessionMapperImpl implements SessionMapper {

    @Autowired
    private MovieService movieService;

    @Autowired
    private HallService hallService;

    @Override
    public SessionCreateData mapToCreateData(CreateSessionDTO sessionDTO) {
        Movie movie = movieService.getMovieById(sessionDTO.getMovieId());
        Hall hall = hallService.getHallById(sessionDTO.getHallId());

        LocalDateTime startTime = LocalDateTime.of(sessionDTO.getDate(), sessionDTO.getTime());
        return new SessionCreateData(startTime, movie, hall, sessionDTO.getPrice());
    }

    @Override
    public SessionUpdateData mapToUpdateData(EditSessionDTO sessionDTO) {
        Movie movie = movieService.getMovieById(sessionDTO.getMovieId());
        Hall hall = hallService.getHallById(sessionDTO.getHallId());

        LocalDateTime startTime = LocalDateTime.of(sessionDTO.getDate(), sessionDTO.getTime());
        return new SessionUpdateData(sessionDTO.getId(), startTime, movie, hall, sessionDTO.getPrice());
    }

    @Override
    public EditSessionDTO mapToEditSessionDTO(Session session) {
        EditSessionDTO editSessionDTO = new EditSessionDTO();
        editSessionDTO.setId(session.getId());
        editSessionDTO.setDate(session.getStartTime().toLocalDate());
        editSessionDTO.setTime(session.getStartTime().toLocalTime());
        editSessionDTO.setMovieId(session.getMovie().getId());
        editSessionDTO.setHallId(session.getHall().getId());
        editSessionDTO.setPrice(session.getPrice());
        editSessionDTO.setMovieTitle(session.getMovie().getTitle());
        return editSessionDTO;
    }

    @Override
    public AdminSessionDTO mapToAdminSessionDTO(Session session) {
        AdminSessionDTO adminSessionDTO = new AdminSessionDTO();
        adminSessionDTO.setId(session.getId());
        adminSessionDTO.setDate(session.getStartTime().toLocalDate());
        adminSessionDTO.setTime(session.getStartTime().toLocalTime());
        adminSessionDTO.setEndTime(session.getEndTime());
        adminSessionDTO.setMovieTitle(session.getMovie().getTitle());
        adminSessionDTO.setHallName(session.getHall().getName());
        adminSessionDTO.setPrice(session.getPrice());
        adminSessionDTO.setActive(session.isActive());
        return adminSessionDTO;
    }
}
