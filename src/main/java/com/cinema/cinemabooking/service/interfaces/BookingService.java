package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.model.Booking;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.model.User;
import com.cinema.cinemabooking.model.enums.BookingStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис броней
 */
public interface BookingService {

    /**
     * Проверяет наличие активных броней в указанном сеансе
     * @param session сеанс
     */
    boolean hasSessionActiveBooking(Session session);

    void createBookings(Session session, List<Long> selectedSeatsIds, User user);

    List<Booking> getActiveUsersBookings(User user);

    List<Booking> getFinishedUsersBookings(User user);

    void cancelBookings(List<Long> bookingIds, User user);

    void updateBookingStatus(Booking booking, BookingStatus status);

    List<Booking> getActiveBookingsForSession(Session session);

    void payBooking(Long id, User user);

    List<Booking> getBookingsByFilters(String movieTitle, LocalDate date, BookingStatus status, String email);
}
