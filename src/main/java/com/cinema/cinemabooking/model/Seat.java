package com.cinema.cinemabooking.model;

import jakarta.persistence.*;

/**
 * Место в зале
 */
@Entity
public class Seat {

    /**
     * Идентификатор места
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Ряд места
     */
    private int seatRow;

    /**
     * Номер места
     */
    private int seatNumber;

    @ManyToOne
    private Hall hall;

    private boolean isActive = true;

    public Seat(int seatRow, int seatNumber, Hall hall) {
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.hall = hall;
    }

    public Seat() {}

    public Long getId() {
        return id;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
