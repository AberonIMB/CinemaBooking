package com.cinema.cinemabooking.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * Кинозал
 */
@Entity
public class Hall {

    /**
     * Идентификатор кинозала
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название кинозала
     */
    private String name;

    /**
     * Количество рядов
     */
    private int numberOfRows;

    /**
     * Количество мест в ряду
     */
    private int seatsInRow;

    /**
     * Список мест в кинозале
     */
    @OneToMany(mappedBy = "hall")
    private List<Seat> seats;

    private boolean isActive = true;

    public Hall(String name, int numberOfRows, int seatsInRow) {
        this.name = name;
        this.numberOfRows = numberOfRows;
        this.seatsInRow = seatsInRow;
    }

    public Hall() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsInRow() {
        return seatsInRow;
    }

    public void setSeatsInRow(int seatsInRow) {
        this.seatsInRow = seatsInRow;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return Objects.equals(id, hall.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
