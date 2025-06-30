package com.cinema.cinemabooking.dto.seat;

import com.cinema.cinemabooking.model.Seat;

public class SeatDTO {
    private Long id;
    private int rowNumber;
    private int seatNumber;
    private boolean isBooked;

    public SeatDTO() {

    }

    public SeatDTO(Seat seat, boolean isBooked) {
        this.id = seat.getId();
        this.rowNumber = seat.getSeatRow();
        this.seatNumber = seat.getSeatNumber();
        this.isBooked = isBooked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
