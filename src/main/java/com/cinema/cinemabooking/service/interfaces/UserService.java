package com.cinema.cinemabooking.service.interfaces;

import com.cinema.cinemabooking.dto.user.RegisterDTO;
import com.cinema.cinemabooking.model.User;

/**
 * Сервис для работы с пользователями
 */
public interface UserService {

    /**
     * Поиск пользователя по email
     */
    User getUserByEmail(String email);

    /**
     * Создание пользователя
     */
    void saveUser(RegisterDTO userDTO);
}
