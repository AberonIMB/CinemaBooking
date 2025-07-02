package com.cinema.cinemabooking.repository;

import com.cinema.cinemabooking.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для залов
 */
public interface HallRepository extends JpaRepository<Hall, Long> {

    /**
     * Получить список залов с определенным статусом
     * @param isActive статус
     */
    List<Hall> getHallsByIsActive(boolean isActive);

    /**
     * Проверяет существование зала по названию
     * @param name название
     */
    boolean existsByName(String name);

    /**
     * Получить зал по названию
     * @param name название
     */
    Hall getHallByName(String name);
}
