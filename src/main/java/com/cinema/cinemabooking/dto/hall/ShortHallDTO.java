package com.cinema.cinemabooking.dto.hall;

/**
 * DTO для отображения краткой информации о зале,а именно id, название и статус
 */
public class ShortHallDTO {

    private Long id;

    private String name;

    private boolean isActive;

    public ShortHallDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
