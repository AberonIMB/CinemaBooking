package com.cinema.cinemabooking.controller.api;

import com.cinema.cinemabooking.dto.booking.AdminUserInfoBookingDTO;
import com.cinema.cinemabooking.mapper.interfaces.BookingMapper;
import com.cinema.cinemabooking.model.enums.BookingStatus;
import com.cinema.cinemabooking.service.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Api контроллер для работы с бронированиями
 */
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingMapper bookingMapper;

    /**
     * Получает список бронирований по фильтрам
     * @param email имя пользователя - email
     * @param movieTitle название фильма
     * @param date дата сеанса
     * @param status статус бронирования
     */
    @GetMapping
    public List<AdminUserInfoBookingDTO> getBookings(@RequestParam(required = false) String email,
                                                     @RequestParam(required = false) String movieTitle,
                                                     @RequestParam(required = false)
                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate date,
                                                     @RequestParam(required = false) String status) {

        BookingStatus bookingStatus = getBookingStatus(status);

        return bookingMapper.mapToAdminUserInfoBookingDTOList(
                bookingService.getBookingsByFilters(movieTitle, date, bookingStatus, email)
        );
    }

    /**
     * Преобразует строку в статус бронирования
     */
    private BookingStatus getBookingStatus(String status) {
        if (status == null) {
            return null;
        }

        try {
            return BookingStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
