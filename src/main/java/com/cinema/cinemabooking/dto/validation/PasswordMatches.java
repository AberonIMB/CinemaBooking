package com.cinema.cinemabooking.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Проверяет, что пользователь ввел одинаковые значения в поля для пароля и подтверждения пароля
 */
@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {

    String message() default "Пароли не совпадают";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
