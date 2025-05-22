package com.project.Lyricys.Controllers;

import com.project.Lyricys.DTOs.LoginRequestDto;
import com.project.Lyricys.DTOs.LoginResponseDto;
import com.project.Lyricys.DTOs.SignupDto;
import com.project.Lyricys.Services.JwtService;
import com.project.Lyricys.Services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final RegistrationService registrationService;
    private final JwtService jwtService;

    public AuthController(RegistrationService registrationService, JwtService jwtService) {
        this.registrationService = registrationService;
        this.jwtService = jwtService;
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
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestBody @Valid String token) {
        return ResponseEntity.ok(jwtService.validateToken(token));
    }
}
