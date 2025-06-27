package com.cinema.cinemabooking.dto.movie;

/**
 * DTO для отображения краткой информации о фильме,а именно id и название
 */
public class ShortMovieDTO {

    private Long id;
    private String title;

    public ShortMovieDTO() {
    }

    public ShortMovieDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
