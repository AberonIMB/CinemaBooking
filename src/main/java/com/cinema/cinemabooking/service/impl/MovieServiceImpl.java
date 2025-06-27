package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.dto.movie.CreateMovieDTO;
import com.cinema.cinemabooking.dto.movie.EditMovieDTO;
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
    public void createMovieFromDTO(CreateMovieDTO createMovieDTO) {
        if (movieRepository.findByTitle(createMovieDTO.getTitle()) != null) {
            throw new MovieAlreadyExistsException(createMovieDTO.getTitle());
        }

        Movie movie = new Movie(
                createMovieDTO.getTitle(),
                createMovieDTO.getDescription(),
                createMovieDTO.getGenres(),
                createMovieDTO.getActors(),
                createMovieDTO.getDirector(),
                createMovieDTO.getDurationInMinutes(),
                createMovieDTO.getReleaseYear()
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
    public void updateMovie(Long id, EditMovieDTO movieDTO) {
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