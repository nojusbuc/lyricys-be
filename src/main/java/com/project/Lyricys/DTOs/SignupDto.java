package com.project.Lyricys.DTOs;

import com.project.Lyricys.Validation.PasswordMatches;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@PasswordMatches
public record SignupDto(
    @NotBlank(message = "First name is required")
    String firstName,

    @NotBlank(message = "Last name is required")
    String lastName,

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    String email,

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    LocalDate dateOfBirth,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password,

    @NotBlank(message = "Confirm password is required")
    String confirmPassword) {



}