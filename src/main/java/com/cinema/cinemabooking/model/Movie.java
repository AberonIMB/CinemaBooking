package com.cinema.cinemabooking.model;

import jakarta.persistence.*;


/**
 * Фильм
 */
@Entity
public class Movie {

    /**
     * Идентификатор фильма
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название фильма
     */
    private String title;

    /**
     * Описание фильма
     */
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Жанры
     */
    private String genres;

    /**
     * Актеры
     */
    private String actors;

    /**
     * Режиссер
     */
    private String director;

    /**
     * Длительность в минутах
     */
    private int durationInMinutes;

    /**
     * Год выпуска
     */
    private int releaseYear;

    public Movie(String title,
                 String description,
                 String genres,
                 String actors,
                 String director,
                 int durationInMinutes,
                 int releaseYear) {

        this.title = title;
        this.description = description;
        this.genres = genres;
        this.actors = actors;
        this.director = director;
        this.durationInMinutes = durationInMinutes;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
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

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
