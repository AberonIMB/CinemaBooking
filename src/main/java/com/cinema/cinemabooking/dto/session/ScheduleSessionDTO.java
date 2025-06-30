package com.cinema.cinemabooking.dto.session;

import java.time.LocalTime;

public class ScheduleSessionDTO {

    private Long id;
    private String hallName;
    private LocalTime time;
    private double price;

    public ScheduleSessionDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
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
