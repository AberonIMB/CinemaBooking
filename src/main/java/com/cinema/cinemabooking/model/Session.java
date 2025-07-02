package com.cinema.cinemabooking.model;

import com.cinema.cinemabooking.dto.session.SessionUpdateData;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @ManyToOne
    private Movie movie;

    /**
     * Зал
     */
    @ManyToOne
    private Hall hall;

    /**
     * Цена билета
     */
    private double price;

    /**
     * Активен ли сеанс
     */
    private boolean isActive = true;

    public Session(LocalDateTime startTime, Movie movie, Hall hall, double price) {
        this.startTime = startTime;
        this.movie = movie;
        this.hall = hall;
        this.price = price;

        this.endTime = startTime.plusMinutes(movie.getDurationInMinutes());
    }

    public Session() { }

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
        this.endTime = startTime.plusMinutes(movie.getDurationInMinutes());
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void update(SessionUpdateData data) {
        this.movie = data.getMovie();
        this.hall = data.getHall();
        this.price = data.getPrice();
        this.startTime = data.getStartTime();
        this.endTime = startTime.plusMinutes(movie.getDurationInMinutes());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        return "Сеанс: " +
                "\nID: " + id +
                "\n, цена: " + price +
                "\n, Зал: " + hall.getName() +
                "\n, Фильм: " + movie.getTitle() +
                "\n, Время начала: " + startTime.format(formatter) +
                "\n, Время конца: " + endTime.format(formatter);
    }
}