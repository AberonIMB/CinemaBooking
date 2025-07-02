package com.cinema.cinemabooking.dto.booking;

import java.util.List;

/**
 * DTO для запроса на бронирования,
 * содержит идентификатор сеанса и список идентификаторов мест для бронирования
 */
public class BookingRequestDTO {

    private Long sessionId;
    private List<Long> selectedSeats;

    public BookingRequestDTO() {

    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public List<Long> getSelectedSeats() {
        return selectedSeats;
    }

    public void setSelectedSeats(List<Long> selectedSeats) {
        this.selectedSeats = selectedSeats;
    }
}
