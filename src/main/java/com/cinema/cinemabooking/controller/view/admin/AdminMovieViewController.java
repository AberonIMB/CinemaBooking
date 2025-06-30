package com.cinema.cinemabooking.controller.view.admin;

import com.cinema.cinemabooking.dto.movie.CreateEditMovieDTO;
import com.cinema.cinemabooking.exception.movie.MovieAlreadyExistsException;
import com.cinema.cinemabooking.mapper.interfaces.MovieMapper;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с фильмами
 */
@Controller
@RequestMapping("/admin/movies")
public class AdminMovieViewController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper movieMapper;

    /**
     * Отображает страницу с фильмами
     */
    @GetMapping("")
    public String movies() {
        return "admin/movie/adminMovies";
    }

    /**
     * Отображает страницу создания фильма
     */
    @GetMapping("/create")
    public String createMovie(Model model) {
        model.addAttribute("movie", new CreateEditMovieDTO());
        return "admin/movie/adminCreateMovie";
    }

    /**
     * Создает фильм из переданного DTO, если ошибок нет, то перенаправляет на страницу с фильмами,
     * иначе добавляет ошибку на страницу
     * @param createEditMovieDTO дто для создания фильма
     * @param bindingResult результат валидации
     * @param model модель
     */
    @PostMapping("/create")
    public String createMovie(@Valid @ModelAttribute("movie") CreateEditMovieDTO createEditMovieDTO,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("movie", createEditMovieDTO);
            return "admin/movie/adminCreateMovie";
        }

        try {
            movieService.createMovieFromDTO(createEditMovieDTO);
        } catch (MovieAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/movie/adminCreateMovie";
        }

        return "redirect:/admin/movies";
    }

    /**
     * Отображает страницу редактирования фильма
     * @param id идентификатор редактируемого фильма
     * @param model модель
     */
    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id);
        CreateEditMovieDTO movieDTO = movieMapper.mapToCreateEditMovieDTO(movie);
        model.addAttribute("movie", movieDTO);
        return "admin/movie/adminEditMovie";
    }

    /**
     * Редактирует фильм, если ошибок нет, то перенаправляет на страницу с фильмами,
     * иначе добавляет ошибку на страницу
     * @param id идентификатор редактируемого фильма
     * @param movieDTO дто для редактирования фильма
     * @param bindingResult результат валидации
     * @param model модель
     */
    @PostMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id,
                            @Valid @ModelAttribute("movie") CreateEditMovieDTO movieDTO,
                            BindingResult bindingResult,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("movie", movieDTO);
            return "admin/movie/adminEditMovie";
        }

        try {
            movieService.updateMovie(id, movieDTO);
        } catch (RuntimeException e) {
            model.addAttribute("movie", movieDTO);
            model.addAttribute("error", e.getMessage());
            return "admin/movie/adminEditMovie";
        }

        return "redirect:/admin/movies";
    }
}