package org.example.barappapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.barappapi.dto.auth.AuthenticationDto;
import org.example.barappapi.reponse.AuthenticationResponse;
import org.example.barappapi.dto.auth.RegisterDto;
import org.example.barappapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterDto request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationDto request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
