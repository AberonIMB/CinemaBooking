package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.dto.booking.BookingSessionDTO;
import com.cinema.cinemabooking.model.Session;

public interface SessionDetailsService {

    BookingSessionDTO getSessionDetails(Session session);
}
