package org.example.barappapi.service;

import lombok.RequiredArgsConstructor;
import org.example.barappapi.model.Barmaker;
import org.example.barappapi.dto.auth.AuthenticationDto;
import org.example.barappapi.reponse.AuthenticationResponse;
import org.example.barappapi.repository.BarmakerRepository;
import org.example.barappapi.dto.auth.RegisterDto;
import org.example.barappapi.service.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final BarmakerRepository barmakerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterDto request) {
        var barmaker = Barmaker.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).name(request.getName()).build();
        if(barmakerRepository.findByEmail(request.getEmail()).isEmpty()){
            barmakerRepository.save(barmaker);
        }
        var jwtToken = jwtService.generateToken(barmaker);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        var barmaker = barmakerRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(barmaker);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
