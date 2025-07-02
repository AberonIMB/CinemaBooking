package com.cinema.cinemabooking.dto.session;

import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Movie;

import java.time.LocalDateTime;

/**
 * Данные для обновления сеанса, которые передаются между контроллером и сервисом
 */
public class SessionUpdateData {

    private Long id;
    private LocalDateTime startTime;
    private Movie movie;
    private Hall hall;
    private double price;

    public SessionUpdateData(Long id, LocalDateTime startTime, Movie movie, Hall hall, double price) {
        this.id = id;
        this.startTime = startTime;
        this.movie = movie;
        this.hall = hall;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}