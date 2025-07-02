package com.cinema.cinemabooking.controller.view.admin;

import com.cinema.cinemabooking.dto.movie.CreateEditMovieDTO;
import com.cinema.cinemabooking.mapper.interfaces.MovieMapper;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * Создает фильм из переданного DTO
     * @param createEditMovieDTO дто для создания фильма
     * @param bindingResult результат валидации
     */
    @PostMapping("/create")
    public String createMovie(@Valid @ModelAttribute("movie") CreateEditMovieDTO createEditMovieDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirect) {
        if (bindingResult.hasErrors()) {
            return "admin/movie/adminCreateMovie";
        }

        movieService.createMovieFromDTO(createEditMovieDTO);

        redirect.addFlashAttribute("success", "Фильм успешно создан");
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
     * Редактирует фильм
     * @param id идентификатор редактируемого фильма
     * @param movieDTO дто для редактирования фильма
     * @param bindingResult результат валидации
     * @param model модель
     */
    @PostMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id,
                            @Valid @ModelAttribute("movie") CreateEditMovieDTO movieDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirect,
                            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("movie", movieDTO);
            return "admin/movie/adminEditMovie";
        }

        movieService.updateMovie(id, movieDTO);

        redirect.addFlashAttribute("success", "Фильм успешно изменен");
        return "redirect:/admin/movies";
    }
}