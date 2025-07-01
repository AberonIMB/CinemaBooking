package com.cinema.cinemabooking.service.scheduler;

import com.cinema.cinemabooking.model.Booking;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.model.enums.BookingStatus;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import com.cinema.cinemabooking.service.interfaces.SessionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class SessionStatusScheduler {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MovieService movieService;

//    @Scheduled(cron = "0 0/10 * * * *")
    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void updateeSessionStatus() {
        LocalDateTime now = LocalDateTime.now();

        List<Session> activeSessions = sessionService.getSessionsByStatus(true);

        for (Session session : activeSessions) {
            if (session.getStartTime().isBefore(now)
                    || Duration.between(now, session.getStartTime()).toMinutes() <= 60) {

                sessionService.updateSessionStatus(session, false);

                updateBookingStatus(session);
            }
        }

        updateMovieStatus();
    }

    private void updateBookingStatus(Session session) {
        List<Booking> bookings = bookingService.getActiveBookingsForSession(session);
        for (Booking booking : bookings) {
            if (booking.getStatus() == BookingStatus.BOOKED) {
                bookingService.updateBookingStatus(booking, BookingStatus.EXPIRED);
            } if (booking.getStatus() == BookingStatus.PAID) {
                bookingService.updateBookingStatus(booking, BookingStatus.COMPLETED);
            }
        }
    }

    private void updateMovieStatus() {
        List<Movie> moviesForDeactivation = movieService.getActiveMoviesWithoutActiveSessions();

        for (Movie movie : moviesForDeactivation) {
            movie.setActive(false);
        }

        movieService.saveAll(moviesForDeactivation);
    }
}