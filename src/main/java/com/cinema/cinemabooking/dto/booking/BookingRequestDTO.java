package com.cinema.cinemabooking.dto.booking;

import java.util.List;

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
