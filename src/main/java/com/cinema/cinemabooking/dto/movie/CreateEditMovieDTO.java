package com.cinema.cinemabooking.dto.movie;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO для редактирования фильма
 */
public class CreateEditMovieDTO {

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

    @NotNull(message = "Продолжительность фильма обязательна для заполнения")
    @Min(value = 60, message = "Минимальная продолжительность фильма - 60 минут")
    @Max(value = 240, message = "Максимальная продолжительность фильма - 240 минут")
    private Integer durationInMinutes;

    @NotNull(message = "Год выпуска обязателен для заполнения")
    @Min(value = 1990, message = "Год выпуска не может быть меньше 1990")
    private Integer releaseYear;

    public CreateEditMovieDTO() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }
}
