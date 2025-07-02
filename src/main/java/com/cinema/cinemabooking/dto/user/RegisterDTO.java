package com.cinema.cinemabooking.dto.user;

import com.cinema.cinemabooking.dto.validation.PasswordMatches;
import jakarta.validation.constraints.*;

/**
 * DTO для регистрации пользователя
 */
@PasswordMatches
public class RegisterDTO {

    @NotBlank(message = "Email обязателен для заполнения")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Полное имя обязательно для заполнения")
    private String fullName;

    @NotBlank(message = "Пароль обязателен для заполнения")
    @Size(min = 6, max = 14, message = "Пароль должен содержать от 6 до 14 символов")
    private String password;

    @NotBlank(message = "Это обязательное поле")
    private String confirmPassword;

    public RegisterDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
