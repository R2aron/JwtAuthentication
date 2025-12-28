package com.example.JwtAuthentication.controller;

import com.example.JwtAuthentication.dto.LoginUserDto;
import com.example.JwtAuthentication.dto.RegisterUserDto;
import com.example.JwtAuthentication.dto.VerifyUserDto;
import com.example.JwtAuthentication.model.User;
import com.example.JwtAuthentication.responses.LoginResponse;
import com.example.JwtAuthentication.service.AuthenticationService;
import com.example.JwtAuthentication.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticatioController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    public AuthenticatioController(JwtService jwtService, AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register (@RequestBody RegisterUserDto registerUserDto)
    {
        User registerUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto)
    {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto)
    {
        try {
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("account verified successfuly");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam String email)
    {
        try {
            authenticationService.resendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
