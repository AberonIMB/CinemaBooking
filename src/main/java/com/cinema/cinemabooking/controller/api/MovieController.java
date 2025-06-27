package com.cinema.cinemabooking.controller.api;

import com.cinema.cinemabooking.dto.movie.AdminMovieDTO;
import com.cinema.cinemabooking.dto.movie.ShortMovieDTO;
import com.cinema.cinemabooking.mapper.interfaces.MovieMapper;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Api контроллер для работы с фильмами
 */
@RestController
@RequestMapping("api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper movieMapper;

    /**
     * Получить список всех активных фильмов
     */
    @GetMapping("/active")
    public List<AdminMovieDTO> getActiveMovies() {
        return movieService.getMoviesByActive(true)
                .stream()
                .map(movieMapper::mapToAdminMovieDTO)
                .toList();
    }

    /**
     * Получить список неактивных фильмов
     */
    @GetMapping("/inactive")
    public List<AdminMovieDTO> getInactiveMovies() {
        return movieService.getMoviesByActive(false)
                .stream()
                .map(movieMapper::mapToAdminMovieDTO)
                .toList();
    }

    /**
     * Получить список фильмов по названию
     * @param title кусок названия фильма
     */
    @GetMapping("/search")
    public List<ShortMovieDTO> searchMovies(@RequestParam("title") String title) {
        return movieService.searchMoviesByTitle(title)
                .stream()
                .map(movieMapper::mapToShortMovieDTO)
                .toList();
    }
}