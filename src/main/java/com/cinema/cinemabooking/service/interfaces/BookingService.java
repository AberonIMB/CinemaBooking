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

    /**
     * Создание броней в указанном сеансе для выбранных мест
     * @param session сеанс
     * @param selectedSeatsIds идентификаторы выбранных мест
     * @param user пользователь
     */
    void createBookings(Session session, List<Long> selectedSeatsIds, User user);

    /**
     * Получить активные брони пользователя
     * @param user пользователь
     * @return
     */
    List<Booking> getActiveUsersBookings(User user);

    /**
     * Получить завершенные брони пользователя
     * @param user пользователь
     * @return
     */
    List<Booking> getFinishedUsersBookings(User user);

    /**
     * Отмена броней
     * @param bookingIds идентификаторы броней
     * @param user пользователь
     */
    void cancelBookings(List<Long> bookingIds, User user);

    /**
     * Обновление статуса брони
     * @param booking бронь
     * @param status статус
     */
    void updateBookingStatus(Booking booking, BookingStatus status);

    /**
     * Получить активные брони для конкретного сеанса
     * @param session сеанс
     */
    List<Booking> getActiveBookingsForSession(Session session);

    /**
     * Имитирует оплату брони
     * @param id идентификатор брони
     * @param user пользователь
     */
    void payBooking(Long id, User user);

    /**
     * Получить список броней по фильтрам
     * @param movieTitle название фильма
     * @param date дата
     * @param status статус
     * @param email никнейм пользователя
     * @return
     */
    List<Booking> getBookingsByFilters(String movieTitle, LocalDate date, BookingStatus status, String email);
}
