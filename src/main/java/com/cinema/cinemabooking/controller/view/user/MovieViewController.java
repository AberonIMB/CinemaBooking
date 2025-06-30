package com.cinema.cinemabooking.controller.view.user;

import com.cinema.cinemabooking.dto.movie.AdminMovieDTO;
import com.cinema.cinemabooking.dto.movie.ShortMovieDTO;
import com.cinema.cinemabooking.mapper.interfaces.MovieMapper;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieViewController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieMapper movieMapper;

    @GetMapping
    public String getMovies(Model model) {

        List<ShortMovieDTO> movies = movieService.getMoviesByActive(true)
                .stream()
                .map(movieMapper::mapToShortMovieDTO)
                .toList();

        model.addAttribute("movies", movies);

        return "user/movies";
    }

    @GetMapping("/{id}")
    public String getMovie(@PathVariable Long id, Model model) {
        AdminMovieDTO movie = movieMapper.mapToAdminMovieDTO(movieService.getMovieById(id));

        model.addAttribute("movie", movie);

        return "user/movie";
    }
}
