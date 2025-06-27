package com.cinema.cinemabooking.repository;

import com.cinema.cinemabooking.model.Booking;
import com.cinema.cinemabooking.model.Session;
import com.cinema.cinemabooking.model.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
