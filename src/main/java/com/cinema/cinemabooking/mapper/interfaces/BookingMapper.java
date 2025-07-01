package com.cinema.cinemabooking.mapper.interfaces;

import com.cinema.cinemabooking.dto.booking.AdminUserInfoBookingDTO;
import com.cinema.cinemabooking.dto.booking.UserInfoBookingDTO;
import com.cinema.cinemabooking.model.Booking;

import java.util.List;

public interface BookingMapper {

    UserInfoBookingDTO mapToUserInfoBookingDTO(Booking booking);

    List<UserInfoBookingDTO> mapToUserInfoBookingDTOList(List<Booking> bookings);

    AdminUserInfoBookingDTO mapToAdminUserInfoBookingDTO(Booking booking);

    List<AdminUserInfoBookingDTO> mapToAdminUserInfoBookingDTOList(List<Booking> bookings);
}
