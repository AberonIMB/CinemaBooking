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
     * Проверяет существование активных броней по сеансу
     * @param session Сеанс
     * @param status Статус(CANCELLED)
     */
    boolean existsBySessionAndStatusNot(Session session, BookingStatus status);

    /**
     * Поиск активных броней(не отмененных) по сеансу и местам
     * @param session Сеанс
     * @param status Статус(CANCELLED)
     * @param seats Места
     * @return Список броней
     */
    List<Booking> findBySessionAndStatusNotAndSeatIn(Session session, BookingStatus status, List<Seat> seats);

    /**
     * Поиск броней с определенным статусом по пользователю
     * @param user Пользователь
     * @param statuses Статусы
     * @return Список броней
     */
    List<Booking> findByUserAndStatusIn(User user, List<BookingStatus> statuses);

    /**
     * Поиск броней по пользователю и идентификатору
     * @param user Пользователь
     * @param bookingIds Идентификаторы броней
     * @return Список броней
     */
    List<Booking> findAllByUserAndIdIn(User user, List<Long> bookingIds);

    /**
     * Поиск активных броней по сеансу
     * @param session Сеанс
     * @param status Статус(CANCELLED)
     * @return Список броней
     */
    List<Booking> findBySessionAndStatusNot(Session session, BookingStatus status);

    /**
     * Поиск броней по фильтрам
     * @param movieTitle Название фильма
     * @param date Дата сеанса
     * @param status Статус брони
     * @param email никнейм пользователя
     * @return Список броней
     */
    @Query("SELECT b FROM Booking b " +
            "WHERE (:movieTitle IS NULL OR LOWER(b.session.movie.title) LIKE LOWER(CONCAT('%', :movieTitle, '%'))) " +
            "AND (:date IS NULL OR FUNCTION('DATE', b.session.startTime) =:date) " +
            "AND (:status IS NULL OR b.status = :status) " +
            "AND (:email IS NULL OR b.user.email = :email)")
    List<Booking> findBookingsByFilters(@Param("movieTitle") String movieTitle,
                                        @Param("date") LocalDate date,
                                        @Param("status") BookingStatus status,
                                        @Param("email") String email);
}
