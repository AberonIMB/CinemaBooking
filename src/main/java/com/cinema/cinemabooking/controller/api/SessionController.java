package com.cinema.cinemabooking.controller.api;

import com.cinema.cinemabooking.dto.session.AdminSessionDTO;
import com.cinema.cinemabooking.mapper.interfaces.SessionMapper;
import com.cinema.cinemabooking.service.interfaces.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Api контроллер для работы с сеансами
 */
@RestController
@RequestMapping("api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionMapper sessionMapper;

    /**
     * Получить список сеансов по фильтрам
     * @param movieTitle название фильма
     * @param hallId идентификатор зала
     * @param date дата
     */
    @GetMapping
    public List<AdminSessionDTO> getSessionsByFilters(@RequestParam(required = false) String movieTitle,
                                                      @RequestParam(required = false) Long hallId,
                                                      @RequestParam(required = false)
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                          LocalDate date) {

        return sessionService.findSessionsByFilters(movieTitle, hallId, date)
                .stream()
                .map(sessionMapper::mapToAdminSessionDTO)
                .toList();
    }
}
