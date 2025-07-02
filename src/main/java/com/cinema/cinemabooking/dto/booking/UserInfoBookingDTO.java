package com.cinema.cinemabooking.dto.booking;

import com.cinema.cinemabooking.model.enums.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * DTO для отображения информации о своих бронированиях
 */
public class UserInfoBookingDTO {

    private Long id;
    private String movieTitle;
    private LocalDate sessionDate;
    private LocalTime sessionTime;
    private LocalDateTime bookingTime;
    private String hallName;
    private int rowNumber;
    private int seatNumber;
    private double price;

    private BookingStatus status;

    public UserInfoBookingDTO() {
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    public LocalTime getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(LocalTime sessionTime) {
        this.sessionTime = sessionTime;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusDescription() {
        switch (status) {
            case BOOKED -> {
                return "Забронирован";
            }
            case COMPLETED -> {
                return "Завершен";
            }
            case PAID -> {
                return "Оплачен";
            }
            case EXPIRED -> {
                return "Просрочен";
            }
            default -> {
                return "Отменен";
            }
        }
    }
}
