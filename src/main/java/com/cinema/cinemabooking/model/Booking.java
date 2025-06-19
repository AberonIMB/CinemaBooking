package com.cinema.cinemabooking.model;

import com.cinema.cinemabooking.model.enums.BookingStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Бронирование
 */
@Entity
public class Booking {

    /**
     * Идентификатор бронирования
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Пользователь, которому принадлежит бронь
     */
    @ManyToOne
    private User user;

    /**
     * Сеанс на который производится бронь
     */
    @ManyToOne
    private Session session;

    /**
     * Забронированное место
     */
    @ManyToOne
    private Seat seat;

    /**
     * Время бронирования
     */
    private LocalDateTime bookingTime;

    /**
     * Статус бронирования
     */
    private BookingStatus status;

    public Booking(User user, Session session, Seat seat) {
        this.user = user;
        this.session = session;
        this.seat = seat;
        this.bookingTime = LocalDateTime.now();
        this.status = BookingStatus.BOOKED;
    }

    public Long getId() {
        return id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}