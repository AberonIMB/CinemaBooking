package com.cinema.cinemabooking.dto.session.validation;

import com.cinema.cinemabooking.dto.session.EditSessionDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

/**
 * Валидатор, который проверяет, что дата и время начала сеанса не в прошлом
 */
public class SessionDateTimeValidator implements ConstraintValidator<SessionDateTimeValid, EditSessionDTO> {

    @Override
    public boolean isValid(EditSessionDTO dto, ConstraintValidatorContext context) {
        if (dto.getDate() == null || dto.getTime() == null) {
            return true;
        }

        LocalDateTime sessionStartTime = LocalDateTime.of(dto.getDate(), dto.getTime());

        LocalDateTime now = LocalDateTime.now();

        if (sessionStartTime.isBefore(now)) {
            context.disableDefaultConstraintViolation();

            if (dto.getDate().isBefore(now.toLocalDate())) {
                handleError(context,
                            "Дата начала сеанса не может быть в прошлом",
                            "date");
            } else {
                handleError(context,
                            "Время начала сеанса не может быть в прошлом",
                            "time");
            }

            return false;
        }

        return true;
    }

    private void handleError(ConstraintValidatorContext context, String errorMessage, String fieldName) {
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addPropertyNode(fieldName)
                .addConstraintViolation();
    }
}
