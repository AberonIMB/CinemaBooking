package com.cinema.cinemabooking.repository;

import com.cinema.cinemabooking.model.Hall;
import com.cinema.cinemabooking.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для мест
 */
public interface SeatRepository extends JpaRepository<Seat, Long> {

    /**
     * Получить список мест по залу и статусу
     * @param hall Зал
     * @param isActive статус
     */
    List<Seat> getSeatsByHallAndIsActive(Hall hall, boolean isActive);
}