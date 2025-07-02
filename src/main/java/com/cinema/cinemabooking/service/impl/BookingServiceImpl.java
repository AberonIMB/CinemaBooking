package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.exception.booking.BookingCancellationException;
import com.cinema.cinemabooking.exception.booking.BookingNotFoundException;
import com.cinema.cinemabooking.exception.booking.BookingPaymentException;
import com.cinema.cinemabooking.exception.booking.SeatAlreadyBookedException;
import com.cinema.cinemabooking.model.Booking;
import com.cinema.cinemabooking.model.Seat;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.model.User;
import com.cinema.cinemabooking.model.enums.BookingStatus;
import com.cinema.cinemabooking.repository.BookingRepository;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import com.cinema.cinemabooking.service.interfaces.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private SeatService seatService;

    @Override
    public boolean hasSessionActiveBooking(Session session) {
        return bookingRepository.existsBySessionAndStatusNot(session, BookingStatus.CANCELLED);
    }

    @Override
    public List<Booking> getActiveUsersBookings(User user) {
        return bookingRepository.findByUserAndStatusIn(user, List.of(BookingStatus.BOOKED, BookingStatus.PAID))
                .stream()
                .sorted(Comparator.comparing(b -> b.getSession().getStartTime()))
                .toList();
    }

    @Override
    public List<Booking> getFinishedUsersBookings(User user) {
        return bookingRepository.findByUserAndStatusIn(user, List.of(BookingStatus.COMPLETED, BookingStatus.EXPIRED))
                .stream()
                .sorted((b1, b2) -> b2.getSession().getStartTime().compareTo(b1.getSession().getStartTime()))
                .toList();
    }

    @Override
    public void updateBookingStatus(Booking booking, BookingStatus status) {
        booking.setStatus(status);
        bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getActiveBookingsForSession(Session session) {
        return bookingRepository.findBySessionAndStatusNot(session, BookingStatus.CANCELLED);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void payBooking(Long id, User user) {
        Booking booking = bookingRepository.findById(id).orElseThrow(BookingNotFoundException::new);

        if (!booking.getUser().equals(user)) {
            throw new BookingNotFoundException();
        }

        if (booking.getStatus() != BookingStatus.BOOKED) {
            throw new BookingPaymentException(booking);
        }

        booking.setStatus(BookingStatus.PAID);
        bookingRepository.save(booking);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createBookings(Session session, List<Long> selectedSeatsIds, User user) {

        List<Seat> seats = selectedSeatsIds.stream()
                .map(seatService::getSeatById)
                .toList();

        List<Booking> bookings = bookingRepository.findBySessionAndStatusNotAndSeatIn(session, BookingStatus.CANCELLED, seats);

        if (!bookings.isEmpty()) {
            throw new SeatAlreadyBookedException(bookings.get(0));
        }

        for (Seat seat : seats) {
            Booking booking = new Booking(user, session, seat);
            bookingRepository.save(booking);
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void cancelBookings(List<Long> bookingIds, User user) {
        List<Booking> bookings = bookingRepository.findAllByUserAndIdIn(user, bookingIds);

        if (bookings.size() != bookingIds.size()) {
            throw new BookingNotFoundException();
        }

        for (Booking booking : bookings) {
            if (booking.getStatus() == BookingStatus.PAID) {
                throw new BookingCancellationException("Невозможно отменить бронь, так как она уже оплачена, " +
                        "а возврат денежных средств за обплаченные брони не реализован)");
            }

            if (booking.getStatus() == BookingStatus.CANCELLED) {
                throw new BookingCancellationException("Невозможно отменить бронь, так как она уже отменена");
            }

            if (booking.getStatus() != BookingStatus.BOOKED) {
                throw new BookingCancellationException("Невозможно отменить бронь, так как сеанс уже завершен");
            }

            updateBookingStatus(booking, BookingStatus.CANCELLED);
        }
    }

    @Override
    public List<Booking> getBookingsByFilters(String movieTitle, LocalDate date, BookingStatus status, String email) {
        return bookingRepository.findBookingsByFilters(movieTitle, date, status, email);
    }
}