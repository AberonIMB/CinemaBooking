package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.model.enums.BookingStatus;
import com.cinema.cinemabooking.repository.BookingRepository;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public boolean hasSessionActiveBooking(Session session) {
        return bookingRepository.existsBySessionAndStatusIn(session, List.of(BookingStatus.BOOKED, BookingStatus.PAID));
    }
}
