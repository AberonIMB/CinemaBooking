package com.cinema.cinemabooking.dto.movie;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO для создания фильма
 */
public class CreateMovieDTO {

    @NotBlank(message = "Название фильма обязательно для заполнения")
    private String title;

    @NotBlank(message = "Описание фильма обязательно для заполнения")
    private String description;

    @NotBlank(message = "Жанры фильма обязательны для заполнения")
    private String genres;

    @NotBlank(message = "Актеры фильма обязательны для заполнения")
    private String actors;

    @NotBlank(message = "Режиссер фильма обязателен для заполнения")
    private String director;

    @Min(value = 60, message = "Минимальная продолжительность фильма - 60 минут")
    private int durationInMinutes;

    @Min(value = 1990, message = "Год выпуска не может быть меньше 1990")
    private int releaseYear;

    public CreateMovieDTO() {

    }

    public @NotBlank(message = "Название фильма обязательно для заполнения") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Название фильма обязательно для заполнения") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Описание фильма обязательно для заполнения") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Описание фильма обязательно для заполнения") String description) {
        this.description = description;
    }

    public @NotBlank(message = "Жанры фильма обязательны для заполнения") String getGenres() {
        return genres;
    }

    public void setGenres(@NotBlank(message = "Жанры фильма обязательны для заполнения") String genres) {
        this.genres = genres;
    }

    public @NotBlank(message = "Актеры фильма обязательны для заполнения") String getActors() {
        return actors;
    }

    public void setActors(@NotBlank(message = "Актеры фильма обязательны для заполнения") String actors) {
        this.actors = actors;
    }

    public @NotBlank(message = "Режиссер фильма обязателен для заполнения") String getDirector() {
        return director;
    }

    public void setDirector(@NotBlank(message = "Режиссер фильма обязателен для заполнения") String director) {
        this.director = director;
    }

    @Min(value = 60, message = "Минимальная продолжительность фильма - 60 минут")
    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(@Min(value = 60, message = "Минимальная продолжительность фильма - 60 минут") int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    @Min(value = 1990, message = "Год выпуска не может быть меньше 1990")
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(@Min(value = 1990, message = "Год выпуска не может быть меньше 1990") int releaseYear) {
        this.releaseYear = releaseYear;
    }
}