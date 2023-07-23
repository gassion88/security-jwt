package com.gassion88.security.jwt.controller;

import com.gassion88.security.jwt.dto.JwtAuthRequestDTO;
import com.gassion88.security.jwt.dto.JwtAuthResponseDTO;
import com.gassion88.security.jwt.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public JwtAuthResponseDTO createAuthToken(@RequestBody JwtAuthRequestDTO jwtRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequestDTO.getUsername(), jwtRequestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequestDTO.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtAuthResponseDTO(token);
    }

}
