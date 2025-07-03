package com.cinema.cinemabooking.dto.hall;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO для редактирования зала
 */
public class EditHallDTO {

    @NotBlank(message = "Название зала не может быть пустым")
    private String name;

    @Min(value = 1, message = "Количество рядов должно быть больше нуля")
    @Max(value = 10, message = "Количество рядов должно быть меньше 10")
    private int numberOfRows;

    @Min(value = 1, message = "Количество мест в ряду должно быть больше нуля")
    @Max(value = 10, message = "Количество мест в ряду должно быть меньше 10")
    private int seatsInRow;

    private boolean isActive;

    public EditHallDTO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getSeatsInRow() {
        return seatsInRow;
    }

    public void setSeatsInRow(int seatsInRow) {
        this.seatsInRow = seatsInRow;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
