package com.cinema.cinemabooking.mapper.interfaces;

import com.cinema.cinemabooking.dto.booking.AdminUserInfoBookingDTO;
import com.cinema.cinemabooking.dto.booking.UserInfoBookingDTO;
import com.cinema.cinemabooking.model.Booking;

import java.util.List;

/**
 * Маппер для сущностей брони
 */
public interface BookingMapper {

    /**
     * Конвертирует бронь в {@link UserInfoBookingDTO}
     * @param booking бронь
     * @return {@link UserInfoBookingDTO}
     */
    UserInfoBookingDTO mapToUserInfoBookingDTO(Booking booking);

    /**
     * Конвертирует список броней в {@link UserInfoBookingDTO}
     * @param bookings список броней
     * @return список {@link UserInfoBookingDTO}
     */
    List<UserInfoBookingDTO> mapToUserInfoBookingDTOList(List<Booking> bookings);

    /**
     * Конвертирует бронь в {@link AdminUserInfoBookingDTO}
     * @param booking бронь
     * @return {@link AdminUserInfoBookingDTO}
     */
    AdminUserInfoBookingDTO mapToAdminUserInfoBookingDTO(Booking booking);

    /**
     * Конвертирует список броней в {@link AdminUserInfoBookingDTO}
     * @param bookings список броней
     * @return список {@link AdminUserInfoBookingDTO}
     */
    List<AdminUserInfoBookingDTO> mapToAdminUserInfoBookingDTOList(List<Booking> bookings);
}
