package com.cinema.cinemabooking.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Проверяет, что минуты кратны 10
 */
@Documented
@Constraint(validatedBy = MinutesTensValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MinutesTens {
    String message() default "Минуты должны быть кратны 10";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
