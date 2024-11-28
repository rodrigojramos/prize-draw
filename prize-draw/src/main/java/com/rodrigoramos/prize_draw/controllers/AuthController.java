package com.rodrigoramos.prize_draw.controllers;

import com.rodrigoramos.prize_draw.dto.LoginRequest;
import com.rodrigoramos.prize_draw.dto.LogoutRequest;
import com.rodrigoramos.prize_draw.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
        authService.logout(request.getToken());
        return ResponseEntity.ok().build();
    }
}
