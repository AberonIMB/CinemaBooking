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
    private int row;

    /**
     * Номер места
     */
    private int seatNumber;

    @ManyToOne
    private Hall hall;

    public Seat(int row, int seatNumber) {
        this.row = row;
        this.seatNumber = seatNumber;
    }

    public Long getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
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
}
