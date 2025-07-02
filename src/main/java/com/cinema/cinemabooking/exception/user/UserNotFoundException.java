package com.cinema.cinemabooking.exception.user;

import com.cinema.cinemabooking.exception.ResourceNotFoundException;

/**
 * Исключение, которое выбрасывается при попытке найти несуществующего пользователя
 */
public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(String email) {
        super("Пользователь с email " + email + " не найден");
    }
}
