package com.gassion88.security.jwt.controller;

import com.gassion88.security.jwt.dto.JwtAuthRequestDTO;
import com.gassion88.security.jwt.dto.JwtAuthResponseDTO;
import com.gassion88.security.jwt.dto.UserRegisteredDTO;
import com.gassion88.security.jwt.dto.UserRegistrationDTO;
import com.gassion88.security.jwt.entity.User;
import com.gassion88.security.jwt.entity.factory.UserFactory;
import com.gassion88.security.jwt.service.UserDetailsServiceImpl;
import com.gassion88.security.jwt.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserFactory userFactory;

    @PostMapping("/auth")
    public JwtAuthResponseDTO createAuthToken(@RequestBody JwtAuthRequestDTO jwtRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequestDTO.getUsername(), jwtRequestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequestDTO.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);

        return new JwtAuthResponseDTO(token);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        //password check
        User user = userFactory.createUserFromDTO(userRegistrationDTO);
        userDetailsService.createNewUser(user);

        return new ResponseEntity<>("message : user created", HttpStatus.CREATED);
    }
}
