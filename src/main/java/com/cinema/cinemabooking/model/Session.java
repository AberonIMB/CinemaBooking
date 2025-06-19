package com.cinema.cinemabooking.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Сеанс
 */
@Entity
public class Session {


    /**
     * Идентификатор сеанса
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Время начала сеанса
     */
    private LocalDateTime startTime;

    /**
     * Время окончания сеанса
     */
    private LocalDateTime endTime;

    /**
     * Фильм
     */
    private Movie movie;

    /**
     * Зал
     */
    private Hall hall;

    /**
     * Цена билета
     */
    private double price;

    public Session(LocalDateTime startTime, Movie movie, Hall hall, double price) {
        this.startTime = startTime;
        this.movie = movie;
        this.hall = hall;
        this.price = price;

        this.endTime = startTime.plusMinutes(movie.getDurationInMinutes());
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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