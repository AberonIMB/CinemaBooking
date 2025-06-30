package com.cinema.cinemabooking.dto.booking;

import com.cinema.cinemabooking.dto.seat.SeatDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BookingSessionDTO {

    private String movieTitle;
    private int movieDuration;

    private String hallName;

    private int rowsCount;
    private int seatsPerRow;

    private List<SeatDTO> seats;

    private Long sessionId;
    private LocalDate date;
    private LocalTime time;
    private double price;

    public BookingSessionDTO() {}

    public BookingSessionDTO(String movieTitle, int movieDuration, String hallName,
                             int rowsCount, int seatsPerRow,
                             List<SeatDTO> seats, Long sessionId,
                             LocalDate date, LocalTime time, double price) {
        this.movieTitle = movieTitle;
        this.movieDuration = movieDuration;
        this.hallName = hallName;
        this.rowsCount = rowsCount;
        this.seatsPerRow = seatsPerRow;
        this.seats = seats;
        this.sessionId = sessionId;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getMovieDuration() {
        return movieDuration;
    }

    public void setMovieDuration(int movieDuration) {
        this.movieDuration = movieDuration;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public void setSeatsPerRow(int seatsPerRow) {
        this.seatsPerRow = seatsPerRow;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}