package com.cinema.cinemabooking.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private int rows;

    /**
     * Количество мест в ряду
     */
    private int seatsInRow;

    /**
     * Список мест в кинозале
     */
    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    public Hall(String name, int rows, int seatsInRow) {
        this.name = name;
        this.rows = rows;
        this.seatsInRow = seatsInRow;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getseatsInRow() {
        return seatsInRow;
    }

    public void setseatsInRow(int seatsInRow) {
        this.seatsInRow = seatsInRow;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
