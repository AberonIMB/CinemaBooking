package com.cinema.cinemabooking.model.enums;

/**
 * Статус брони
 */
public enum BookingStatus {
    BOOKED, //бронь оформлена, но еще не оплачена
    EXPIRED, // бронь не оплачена за 30 минут до начала сеанса
    CANCELLED, // бронь отменена
    PAID, // бронь оплачена
    COMPLETED // бронь завершена
}
