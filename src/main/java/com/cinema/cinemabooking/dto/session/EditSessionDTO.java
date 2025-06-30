package com.cinema.cinemabooking.dto.session;

import com.cinema.cinemabooking.dto.validation.MinutesTens;
import com.cinema.cinemabooking.dto.validation.SessionDateTimeValid;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO для редактирования сеанса
 */
@SessionDateTimeValid
public class EditSessionDTO {

    @NotNull(message = "Сеанса с таким id не существует")
    private Long id;

    @NotNull(message = "Время начала сеанса обязательно")
    @MinutesTens
    private LocalTime time;

    @NotNull(message = "Дата обязательна для выбора")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Фильм обязателен для выбора")
    private Long movieId;

    @NotNull(message = "Зал обязателен для выбора")
    private Long hallId;

    @NotBlank(message = "Название фильма обязательно для заполнения")
    private String movieTitle;

    @NotNull(message = "Цена обязательна")
    @Positive(message = "Цена должна быть положительной")
    private double price;

    public EditSessionDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
