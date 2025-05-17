package com.project.Lyricys.Services;

import com.project.Lyricys.DTOs.LoginRequestDto;
import com.project.Lyricys.DTOs.LoginResponseDto;
import com.project.Lyricys.DTOs.SignupDto;
import com.project.Lyricys.Entities.User;
import com.project.Lyricys.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordConfig;
    private final JwtService jwtService;

    public RegistrationService(UserRepository userRepository, BCryptPasswordEncoder passwordConfig, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
        this.jwtService = jwtService;
    }

    @Transactional
    public LoginResponseDto signupUser(SignupDto signupDto) {

        if (userRepository.existsByEmail(signupDto.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "An account with that email already exists."
            );
        }
        if (!signupDto.password().equals(signupDto.confirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password and confirm password must match."
            );
        }
        User user = new User();
        user.setFirstName(signupDto.firstName());
        user.setLastName(signupDto.lastName());
        user.setEmail(signupDto.email());
        user.setDateOfBirth(signupDto.dateOfBirth());

        Instant now = Instant.now();

        user.setCreatedAt(now);
        user.setLastSeenAt(now);

        user.setPassword(passwordConfig.encode(signupDto.password()));

        User saved = userRepository.save(user);

        String token = jwtService.generateToken(saved.getEmail());
        return new LoginResponseDto(token, user.getId());
    }

    @Transactional
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordConfig.matches(loginRequestDto.password(), user.getPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponseDto(token, user.getId());





    }
}
