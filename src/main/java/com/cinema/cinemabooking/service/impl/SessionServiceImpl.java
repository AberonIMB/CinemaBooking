package com.cinema.cinemabooking.service.impl;

import com.cinema.cinemabooking.dto.session.SessionCreateData;
import com.cinema.cinemabooking.dto.session.SessionUpdateData;
import com.cinema.cinemabooking.exception.hall.HallIsNotActiveException;
import com.cinema.cinemabooking.exception.session.ActiveBookingsExistException;
import com.cinema.cinemabooking.exception.session.SessionNotFoundException;
import com.cinema.cinemabooking.exception.session.SessionOverlapException;
import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.repository.SessionRepository;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import com.cinema.cinemabooking.service.interfaces.MovieService;
import com.cinema.cinemabooking.service.interfaces.SessionService;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElseThrow(() -> new SessionNotFoundException(id));
    }

    @Override
    @Transactional
    public void createSession(SessionCreateData sessionData) {

        Hall hall = sessionData.getHall();
        LocalDateTime sessionStartTime = sessionData.getStartTime();
        LocalDateTime sessionEndTime = sessionStartTime.plusMinutes(sessionData.getMovie().getDurationInMinutes());

        validateSessionTimeNotOverlap(hall, sessionStartTime, sessionEndTime, sessionStartTime.toLocalDate(), null);

        Session session = new Session(sessionStartTime, sessionData.getMovie(), hall, sessionData.getPrice());
        sessionRepository.save(session);
        movieService.activateMovie(session.getMovie());
    }

    @Override
    public List<Session> findSessionsByFilters(String movieTitle, Long hallId, LocalDate date) {
        return sessionRepository.findSessionsByFilters(movieTitle, hallId, date);
    }

    @Override
    @Transactional
    public void updateSession(SessionUpdateData data) {
        Session session = sessionRepository.findById(data.getId())
                .orElseThrow(() -> new SessionNotFoundException(data.getId()));

        boolean hasActiveBooking = bookingService.hasSessionActiveBooking(session);

        if (hasActiveBooking) {
            throw new ActiveBookingsExistException(session);
        }

        validateSessionTimeNotOverlap(data.getHall(),
                data.getStartTime(),
                data.getStartTime().plusMinutes(session.getMovie().getDurationInMinutes()),
                data.getStartTime().toLocalDate(),
                data.getId());

        session.update(data);

        sessionRepository.save(session);
        movieService.activateMovie(session.getMovie());
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
