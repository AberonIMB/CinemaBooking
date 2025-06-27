package com.cinema.cinemabooking.dto.validation;

import com.cinema.cinemabooking.dto.user.RegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Валидатор для сравнения пароля и подтверждения пароля
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, RegisterDTO> {

    @Override
    public boolean isValid(RegisterDTO registerDTO, ConstraintValidatorContext context) {

        String password = registerDTO.getPassword();
        String confirmPassword = registerDTO.getConfirmPassword();

        if (password == null || confirmPassword == null) {
            return true;
        }

        if (!password.equals(confirmPassword)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Пароли не совпадают")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}