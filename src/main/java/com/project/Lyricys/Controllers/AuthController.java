package com.project.Lyricys.Controllers;

import com.project.Lyricys.DTOs.LoginRequestDto;
import com.project.Lyricys.DTOs.LoginResponseDto;
import com.project.Lyricys.DTOs.SignupDto;
import com.project.Lyricys.Entities.User;
import com.project.Lyricys.Repositories.UserRepository;
import com.project.Lyricys.Services.JwtService;
import com.project.Lyricys.Services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginResponseDto> signup(
            @RequestBody @Valid SignupDto dto) {
        LoginResponseDto resp = registrationService.signupUser(dto);
        return ResponseEntity.status(201).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody @Valid LoginRequestDto dto) {
        return ResponseEntity.ok(registrationService.loginUser(dto));
    }
}
