package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.dto.movie.CreateEditMovieDTO;
import com.cinema.cinemabooking.exception.movie.CannotChangeDurationException;
import com.cinema.cinemabooking.exception.movie.MovieAlreadyExistsException;
import com.cinema.cinemabooking.exception.movie.MovieNotFoundException;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.repository.MovieRepository;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getMoviesByActive(boolean isActive) {
        return movieRepository.findByIsActive(isActive);
    }

    @Override
    public void createMovieFromDTO(CreateEditMovieDTO createEditMovieDTO) {
        if (movieRepository.findByTitle(createEditMovieDTO.getTitle()) != null) {
            throw new MovieAlreadyExistsException(createEditMovieDTO.getTitle());
        }

        Movie movie = new Movie(
                createEditMovieDTO.getTitle(),
                createEditMovieDTO.getDescription(),
                createEditMovieDTO.getGenres(),
                createEditMovieDTO.getActors(),
                createEditMovieDTO.getDirector(),
                createEditMovieDTO.getDurationInMinutes(),
                createEditMovieDTO.getReleaseYear()
        );

        movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException(id));
    }

    @Override
    @Transactional
    public void updateMovie(Long id, CreateEditMovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new MovieNotFoundException(id));

        Movie existingMovie = movieRepository.findByTitle(movieDTO.getTitle());

        if (existingMovie != null && !existingMovie.getId().equals(id)) {
            throw new MovieAlreadyExistsException(movieDTO.getTitle());
        }

        if (movie.isActive() && movieDTO.getDurationInMinutes() != movie.getDurationInMinutes()) {
            throw new CannotChangeDurationException(movie);
        }

        movie.update(movieDTO);

        movieRepository.save(movie);
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void activateMovie(Movie movie) {
        movie.setActive(true);
        movieRepository.save(movie);
    }

    @Override
    public void deactivateMovie(Movie movie) {
        movie.setActive(false);
        movieRepository.save(movie);
    }
}