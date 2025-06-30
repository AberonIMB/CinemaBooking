package com.cinema.cinemabooking.exception.user;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("Пользователь с email " + email + " не найден");
    }
}
