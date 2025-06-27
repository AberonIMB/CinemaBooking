package com.cinema.cinemabooking.exception.user;

/**
 * Исключение, возникающее при попытке регистрации пользователя с уже зарегистрированной почтой
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {
        super("Пользователь с таким email уже существует");
    }
}
