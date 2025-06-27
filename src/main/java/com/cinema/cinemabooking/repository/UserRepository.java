package com.cinema.cinemabooking.repository;

import com.cinema.cinemabooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с пользователями
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Поиск пользователя по email
     */
    User findByEmail(String email);

    /**
     * Проверка существования пользователя по email
     */
    boolean existsByEmail(String email);
}
