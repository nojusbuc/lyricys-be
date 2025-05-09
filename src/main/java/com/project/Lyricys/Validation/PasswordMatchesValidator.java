package com.project.Lyricys.Validation;

import com.project.Lyricys.DTOs.SignupDto;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignupDto> {
    @Override
    public boolean isValid(SignupDto signupDto, ConstraintValidatorContext context) {
        if (signupDto.password() == null || signupDto.confirmPassword() == null) {
            return false;
        }
        return signupDto.password().equals(signupDto.confirmPassword());
    }
}
