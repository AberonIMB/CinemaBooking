package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.dto.session.SessionCreateData;
import com.cinema.cinemabooking.dto.session.SessionUpdateData;
import com.cinema.cinemabooking.exception.session.HallIsNotActiveException;
import com.cinema.cinemabooking.exception.session.*;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Movie;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.repository.SessionRepository;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import com.cinema.cinemabooking.service.interfaces.SessionService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MovieService movieService;

    @Override
    public List<Session> getActiveSessionsByHall(Hall hall) {
        return sessionRepository.getSessionsByIsActiveTrueAndHall(hall);
    }

    @Override
    public List<Session> getActiveSessionsByDate(LocalDate date) {
        return sessionRepository.findActiveSessionsByDate(date);
    }

    @Override
    public List<Session> getActiveSessionsByMovieAndDate(Movie movie, LocalDate date) {
        return sessionRepository.findActiveSessionsByMovieAndDate(movie, date);
    }

    @Override
    public List<Session> getSessionsByStatus(boolean status) {
        return sessionRepository.findByIsActive(status);
    }

    @Override
    public Session getSessionById(Long id) {
        Session session =  sessionRepository.findById(id)
                .orElseThrow(() -> new SessionNotFoundException(id));

        if (!session.isActive()) {
            throw new SessionAlreadyFinishedException(session);
        }

        return session;
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void createSession(SessionCreateData sessionData) {

        Hall hall = sessionData.getHall();
        LocalDateTime sessionStartTime = sessionData.getStartTime();
        LocalDateTime sessionEndTime = sessionStartTime.plusMinutes(sessionData.getMovie().getDurationInMinutes());

        validateSessionTimeNotOverlap(hall, sessionStartTime, sessionEndTime, sessionStartTime.toLocalDate(), null);

        Session session = new Session(sessionStartTime, sessionData.getMovie(), hall, sessionData.getPrice());
        sessionRepository.save(session);

        if (!session.getMovie().isActive()) {
            movieService.activateMovie(session.getMovie());
        }
    }

    @Override
    public List<Session> findSessionsByFilters(String movieTitle, Long hallId, LocalDate date) {
        return sessionRepository.findSessionsByFilters(movieTitle, hallId, date)
                .stream()
                .sorted(Comparator.comparing(Session::getStartTime).reversed())
                .toList();
    }

    @Override
    public void cancelSession(Session session) {
        if (bookingService.hasSessionActiveBooking(session)) {
            throw new CancelSessionWithActiveBookingsException();
        }

        updateSessionStatus(session, false);
    }

    @Override
    @Transactional
    public void updateSessionStatus(Session session, boolean status) {
        session.setActive(status);
        sessionRepository.save(session);
    }



    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateSession(SessionUpdateData data) {
        Session session = sessionRepository.findById(data.getId())
                .orElseThrow(() -> new SessionNotFoundException(data.getId()));

        boolean hasActiveBooking = bookingService.hasSessionActiveBooking(session);

        if (hasActiveBooking) {
            throw new EditSessionWithActiveBookingsException(session);
        }

        validateSessionTimeNotOverlap(data.getHall(),
                data.getStartTime(),
                data.getStartTime().plusMinutes(session.getMovie().getDurationInMinutes()),
                data.getStartTime().toLocalDate(),
                data.getId());

        session.update(data);

        sessionRepository.save(session);

        if (!session.getMovie().isActive()) {
            movieService.activateMovie(session.getMovie());
        }
    }

    /**
     * Проверяет, что зал создаваемого или редактируемого сеанса активен,
     * а время начала и конца сеанса не пересекаются с другими сеансами
     * @param hall зал
     * @param sessionStartTime начало сеанса
     * @param sessionEndTime конец сеанса
     * @param date дата
     * @param excludeSessionId идентификатор редактируемого сеанса для исключения из проверки на пересечения
     */
    private void validateSessionTimeNotOverlap(Hall hall,
                                               LocalDateTime sessionStartTime,
                                               LocalDateTime sessionEndTime,
                                               LocalDate date,
                                               @Nullable Long excludeSessionId) {

        if (!hall.isActive()) {
            throw new HallIsNotActiveException(hall.getId());
        }

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<Session> sessions = sessionRepository.findActiveSessionsByHallIdAndDateOverlap(
                hall,
                startOfDay,
                endOfDay
        );

        for (Session session : sessions) {

            if (session.getId().equals(excludeSessionId)) {
                continue;
            }

            boolean overlaps = sessionStartTime.isBefore(session.getEndTime())
                    && session.getStartTime().isBefore(sessionEndTime);

            if (overlaps) {
                throw new SessionOverlapException(session);
            }
        }
    }
}
