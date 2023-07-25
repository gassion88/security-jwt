package com.gassion88.security.jwt.controller;

import com.gassion88.security.jwt.dto.JwtAuthRequestDTO;
import com.gassion88.security.jwt.dto.JwtAuthResponseDTO;
import com.gassion88.security.jwt.dto.UserRegistrationDTO;
import com.gassion88.security.jwt.entity.User;
import com.gassion88.security.jwt.entity.factory.UserFactory;
import com.gassion88.security.jwt.service.AuthService;
import com.gassion88.security.jwt.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserFactory userFactory;

    @PostMapping("/auth")
    public JwtAuthResponseDTO createAuthToken(@RequestBody JwtAuthRequestDTO jwtAuthRequestDTO) {
        return authService.createAuthToken(jwtAuthRequestDTO);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        //password check
        User newUser = userFactory.parseUserFromDTO(userRegistrationDTO);
        User createdUser = authService.saveUser(newUser);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
