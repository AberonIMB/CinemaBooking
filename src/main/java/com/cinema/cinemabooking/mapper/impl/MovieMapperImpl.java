package com.cinema.cinemabooking.mapper.impl;

import com.cinema.cinemabooking.dto.movie.AdminMovieDTO;
import com.cinema.cinemabooking.dto.movie.EditMovieDTO;
import com.cinema.cinemabooking.dto.movie.ShortMovieDTO;
import com.cinema.cinemabooking.mapper.interfaces.MovieMapper;
import com.cinema.cinemabooking.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public AdminMovieDTO mapToAdminMovieDTO(Movie movie) {
        AdminMovieDTO adminMovieDTO = new AdminMovieDTO();
        adminMovieDTO.setId(movie.getId());
        adminMovieDTO.setTitle(movie.getTitle());
        adminMovieDTO.setDescription(movie.getDescription());
        adminMovieDTO.setGenres(movie.getGenres());
        adminMovieDTO.setActors(movie.getActors());
        adminMovieDTO.setDirector(movie.getDirector());
        adminMovieDTO.setDurationInMinutes(movie.getDurationInMinutes());
        adminMovieDTO.setReleaseYear(movie.getReleaseYear());
        return adminMovieDTO;
    }

    @Override
    public ShortMovieDTO mapToShortMovieDTO(Movie movie) {
        ShortMovieDTO shortMovieDTO = new ShortMovieDTO();
        shortMovieDTO.setId(movie.getId());
        shortMovieDTO.setTitle(movie.getTitle());
        return shortMovieDTO;
    }

    @Override
    public EditMovieDTO mapToEditMovieDTO(Movie movie) {
        EditMovieDTO editMovieDTO = new EditMovieDTO();
        editMovieDTO.setTitle(movie.getTitle());
        editMovieDTO.setDescription(movie.getDescription());
        editMovieDTO.setGenres(movie.getGenres());
        editMovieDTO.setActors(movie.getActors());
        editMovieDTO.setDirector(movie.getDirector());
        editMovieDTO.setDurationInMinutes(movie.getDurationInMinutes());
        editMovieDTO.setReleaseYear(movie.getReleaseYear());
        return editMovieDTO;
    }
}
