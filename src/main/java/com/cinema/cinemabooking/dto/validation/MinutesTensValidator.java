package com.cinema.cinemabooking.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalTime;

/**
 * Валидатор, проверяющий, что минуты кратны 10
 */
public class MinutesTensValidator implements ConstraintValidator<MinutesTens, LocalTime> {

    @Override
    public boolean isValid(LocalTime localTime, ConstraintValidatorContext constraintValidatorContext) {

        if (localTime == null) {
            return true;
        }

        return localTime.getMinute() % 10 == 0;
    }
}
