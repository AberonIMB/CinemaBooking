package com.cinema.cinemabooking.dto.hall;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO для отображения информации о зале
 */
public class AdminHallDTO {

    @NotNull(message = "Зал не существует")
    private Long id;

    @NotBlank(message = "Название зала не может быть пустым")
    private String name;

    @Min(value = 1, message = "Количество рядов должно быть больше нуля")
    @Max(value = 20, message = "Количество рядов должно быть меньше 20")
    private int numberOfRows;

    @Min(value = 1, message = "Количество мест в ряду должно быть больше нуля")
    @Max(value = 20, message = "Количество мест в ряду должно быть меньше 20")
    private int seatsInRow;
    private boolean isActive;

    public AdminHallDTO() {

    }

    public @NotNull(message = "Зал не существует") Long getId() {
        return id;
    }

    public void setId(@NotNull(message = "Зал не существует") Long id) {
        this.id = id;
    }

    @Min(value = 1, message = "Количество рядов должно быть больше нуля")
    @Max(value = 20, message = "Количество рядов должно быть меньше 20")
    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(@Min(value = 1, message = "Количество рядов должно быть больше нуля") @Max(value = 20, message = "Количество рядов должно быть меньше 20") int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public @NotBlank(message = "Название зала не может быть пустым") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Название зала не может быть пустым") String name) {
        this.name = name;
    }

    @Min(value = 1, message = "Количество мест в ряду должно быть больше нуля")
    @Max(value = 20, message = "Количество мест в ряду должно быть меньше 20")
    public int getSeatsInRow() {
        return seatsInRow;
    }

    public void setSeatsInRow(@Min(value = 1, message = "Количество мест в ряду должно быть больше нуля") @Max(value = 20, message = "Количество мест в ряду должно быть меньше 20") int seatsInRow) {
        this.seatsInRow = seatsInRow;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
