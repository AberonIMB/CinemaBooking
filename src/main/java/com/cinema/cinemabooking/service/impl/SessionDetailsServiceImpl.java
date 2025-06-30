package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.dto.seat.SeatDTO;
import com.cinema.cinemabooking.dto.booking.BookingSessionDTO;
import com.cinema.cinemabooking.model.*;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import com.cinema.cinemabooking.service.interfaces.SeatService;
import com.cinema.cinemabooking.service.interfaces.SessionDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SessionDetailsServiceImpl implements SessionDetailsService {

    @Autowired
    private SeatService seatService;

    @Autowired
    private BookingService bookingService;

    @Override
    public BookingSessionDTO getSessionDetails(Session session) {
        Hall hall = session.getHall();
        Movie movie = session.getMovie();

        String movieTitle = movie.getTitle();
        int movieDuration = movie.getDurationInMinutes();

        String hallName = hall.getName();
        int rowsCount = hall.getNumberOfRows();
        int seatsPerRow = hall.getSeatsInRow();
        List<Seat> hallSeats = seatService.getSeatsForHall(hall);

        List<Booking> bookings = bookingService.getBookings(session);
        Set<Long> bookedSeatIds = bookings
                .stream()
                .map(booking -> booking.getSeat().getId())
                .collect(Collectors.toSet());

        List<SeatDTO> sessionSeats = hallSeats
                .stream()
                .map(seat -> new SeatDTO(seat, bookedSeatIds.contains(seat.getId())))
                .toList();

        double price = session.getPrice();

        LocalDateTime startTime = session.getStartTime();
        LocalDate date = startTime.toLocalDate();
        LocalTime time = startTime.toLocalTime();

        return new BookingSessionDTO(movieTitle,
                movieDuration,
                hallName,
                rowsCount, seatsPerRow,
                sessionSeats,
                session.getId(),
                date, time,
                price);
    }
}
