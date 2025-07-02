package com.cinema.cinemabooking.dto.session;


import com.cinema.cinemabooking.dto.validation.MinutesTens;
import com.cinema.cinemabooking.model.Session;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO для создания сеанса
 */
public class CreateSessionDTO {

    @NotNull(message = "Дата обязательна для выбора")
    @Future(message = "Дата должна быть в будущем")
    private LocalDate date;

    @NotNull(message = "Время начала сеанса обязательно")
    @MinutesTens
    private LocalTime time;

    @NotNull(message = "Фильм обязателен для выбора")
    private Long movieId;

    @NotNull(message = "Зал обязателен для выбора")
    private Long hallId;

    @Positive(message = "Цена должна быть положительной")
    private double price;

    public CreateSessionDTO() {

    }

    public CreateSessionDTO(Session session) {
        this.date = session.getStartTime().toLocalDate();
        this.time = session.getStartTime().toLocalTime();
        this.movieId = session.getMovie().getId();
        this.hallId = session.getHall().getId();
        this.price = session.getPrice();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getHallId() {
        return hallId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}