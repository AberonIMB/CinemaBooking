package com.cinema.cinemabooking.controller.api;

import com.cinema.cinemabooking.dto.ScheduleDTO;
import com.cinema.cinemabooking.dto.session.ScheduleSessionDTO;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import com.cinema.cinemabooking.service.interfaces.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Api контроллер для работы с расписанием
 */
@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MovieService movieService;

    /**
     * Получает расписание сеансов по дате
     * @param date дата
     */
    @GetMapping
    public List<ScheduleDTO> getScheduleByDate(@RequestParam("date")
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                             LocalDate date) {

        return scheduleService.getScheduleByDate(date);
    }

    /**
     * Получает расписание сеансов по фильму
     * @param id идентификатор фильма
     * @param date дата
     */
    @GetMapping("/movie/{id}")
    public List<ScheduleSessionDTO> getScheduleByMovie(@PathVariable Long id,
                                                       @RequestParam("date")
                                                       @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                       LocalDate date) {

        Movie movie = movieService.getMovieById(id);

        return scheduleService.getScheduleByMovie(movie, date);
    }
}