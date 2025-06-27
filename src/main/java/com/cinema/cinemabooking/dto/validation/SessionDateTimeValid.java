package com.cinema.cinemabooking.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Проверяет, что дата и время редактируемого сеанса не в прошлом
 */
@Documented
@Constraint(validatedBy = SessionDateTimeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionDateTimeValid {
    String message() default "Дата и время начала сеанса не могут быть в прошлом";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
