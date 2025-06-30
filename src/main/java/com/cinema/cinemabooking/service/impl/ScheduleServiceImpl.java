package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.dto.ScheduleDTO;
import com.cinema.cinemabooking.dto.movie.ScheduleMovieDTO;
import com.cinema.cinemabooking.dto.session.ScheduleSessionDTO;
import com.cinema.cinemabooking.mapper.interfaces.MovieMapper;
import com.cinema.cinemabooking.mapper.interfaces.SessionMapper;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import com.cinema.cinemabooking.service.interfaces.ScheduleService;
import com.cinema.cinemabooking.service.interfaces.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private MovieService movieService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SessionMapper sessionMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<ScheduleDTO> getScheduleByDate(LocalDate date) {

        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();

        List<Session> activeSessionsByDate = sessionService.getActiveSessionsByDate(date);

        Map<Movie, List<Session>> movieSessionMap = activeSessionsByDate.stream()
                .collect(Collectors.groupingBy(Session::getMovie));

        for (Map.Entry<Movie, List<Session>> entry : movieSessionMap.entrySet()) {
            List<ScheduleSessionDTO> scheduleSessionDTOS = entry.getValue().stream()
                    .map(sessionMapper::mapToScheduleSessionDTO)
                    .toList();

            ScheduleMovieDTO scheduleMovieDTO = movieMapper.mapToScheduleMovieDTO(entry.getKey());
            scheduleDTOList.add(new ScheduleDTO(scheduleMovieDTO, scheduleSessionDTOS));
        }

        return scheduleDTOList;
    }

    @Override
    public List<ScheduleSessionDTO> getScheduleByMovie(Movie movie, LocalDate date) {
        return sessionService.getActiveSessionsByMovieAndDate(movie, date)
                .stream()
                .map(sessionMapper::mapToScheduleSessionDTO)
                .toList();
    }
}