package com.cinema.cinemabooking.mapper.impl;

import com.cinema.cinemabooking.dto.movie.AdminMovieDTO;
import com.cinema.cinemabooking.dto.movie.CreateEditMovieDTO;
import com.cinema.cinemabooking.dto.movie.ScheduleMovieDTO;
import com.cinema.cinemabooking.dto.movie.ShortMovieDTO;
import com.cinema.cinemabooking.mapper.interfaces.MovieMapper;
import com.cinema.cinemabooking.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapperImpl implements MovieMapper {


    @Override
    public List<AdminMovieDTO> mapToAdminMovieDTOList(List<Movie> movieList) {
        return movieList.stream()
                .map(this::mapToAdminMovieDTO)
                .toList();
    }

    @Override
    public List<ShortMovieDTO> mapToShortMovieDTOList(List<Movie> movieList) {
        return movieList.stream()
                .map(this::mapToShortMovieDTO)
                .toList();
    }

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
    public CreateEditMovieDTO mapToCreateEditMovieDTO(Movie movie) {
        CreateEditMovieDTO createEditMovieDTO = new CreateEditMovieDTO();
        createEditMovieDTO.setTitle(movie.getTitle());
        createEditMovieDTO.setDescription(movie.getDescription());
        createEditMovieDTO.setGenres(movie.getGenres());
        createEditMovieDTO.setActors(movie.getActors());
        createEditMovieDTO.setDirector(movie.getDirector());
        createEditMovieDTO.setDurationInMinutes(movie.getDurationInMinutes());
        createEditMovieDTO.setReleaseYear(movie.getReleaseYear());
        return createEditMovieDTO;
    }

    @Override
    public ScheduleMovieDTO mapToScheduleMovieDTO(Movie movie) {
        ScheduleMovieDTO scheduleMovieDTO = new ScheduleMovieDTO();
        scheduleMovieDTO.setId(movie.getId());
        scheduleMovieDTO.setTitle(movie.getTitle());
        scheduleMovieDTO.setReleaseYear(movie.getReleaseYear());
        scheduleMovieDTO.setGenres(movie.getGenres());
        scheduleMovieDTO.setDuration(movie.getDurationInMinutes());
        return scheduleMovieDTO;
    }
}
