//package com.project.Lyricys.Controllers;
//
//import com.project.Lyricys.DTOs.LoginRequestDto;
//import com.project.Lyricys.DTOs.LoginResponseDto;
//import com.project.Lyricys.DTOs.SignupDto;
//import com.project.Lyricys.DTOs.UserDto;
//import com.project.Lyricys.Services.RegistrationService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class RegistrationController {
//
//    private final RegistrationService registrationService;
//
//    public RegistrationController(RegistrationService registrationService) {
//        this.registrationService = registrationService;
//    }
//
//
//    @PostMapping("/signup")
//    public ResponseEntity<UserDto> signupUser(@RequestBody @Valid SignupDto signupDto) {
//        UserDto user = registrationService.signupUser(signupDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(user);
//    }
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody @Valid LoginRequestDto loginRequestDto) {
//        LoginResponseDto response = registrationService.loginUser(loginRequestDto).getBody();
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
//    }
//
//}
