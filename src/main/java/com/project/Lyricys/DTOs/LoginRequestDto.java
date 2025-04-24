package com.project.Lyricys.DTOs;

import jakarta.validation.constraints.*;

public record LoginDto (

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    String email,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    String password) {

}
