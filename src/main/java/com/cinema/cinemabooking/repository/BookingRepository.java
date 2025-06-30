package com.cinema.cinemabooking.repository;

import com.cinema.cinemabooking.model.Booking;
import com.cinema.cinemabooking.model.Seat;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.model.User;
import com.cinema.cinemabooking.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для броней
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /**
     * Проверяет существование брони по сеансу и статусу
     * @param session Сеанс
     * @param statuses статусы брони
     */
    boolean existsBySessionAndStatusIn(Session session, List<BookingStatus> statuses);

    List<Booking> findBySessionAndSeatIn(Session session, List<Seat> seat);

    List<Booking> findByUserAndStatusIn(User user, List<BookingStatus> statuses);

    List<Booking> findAllByUserAndIdIn(User user, List<Long> bookingIds);

    List<Booking> findBySession(Session session);
}
