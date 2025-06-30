package com.cinema.cinemabooking.mapper.impl;

import com.cinema.cinemabooking.dto.booking.UserInfoBookingDTO;
import com.cinema.cinemabooking.mapper.interfaces.BookingMapper;
import com.cinema.cinemabooking.model.Booking;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Seat;
import com.cinema.cinemabooking.model.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public UserInfoBookingDTO mapToUserInfoBookingDTO(Booking booking) {
        UserInfoBookingDTO bookingDTO = new UserInfoBookingDTO();

        Session session = booking.getSession();
        Seat seat = booking.getSeat();
        Hall hall = seat.getHall();

        bookingDTO.setId(booking.getId());
        bookingDTO.setMovieTitle(session.getMovie().getTitle());
        bookingDTO.setSessionDate(session.getStartTime().toLocalDate());
        bookingDTO.setSessionTime(session.getStartTime().toLocalTime());
        bookingDTO.setBookingTime(booking.getBookingTime());
        bookingDTO.setHallName(hall.getName());
        bookingDTO.setRowNumber(seat.getSeatRow());
        bookingDTO.setSeatNumber(seat.getSeatNumber());
        bookingDTO.setPrice(session.getPrice());
        bookingDTO.setStatus(booking.getStatus());

        return bookingDTO;
    }

    @Override
    public List<UserInfoBookingDTO> mapToUserInfoBookingDTOList(List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapToUserInfoBookingDTO)
                .toList();
    }
}
