package com.gassion88.security.jwt.service;

import com.gassion88.security.jwt.dto.JwtAuthRequestDTO;
import com.gassion88.security.jwt.dto.JwtAuthResponseDTO;
import com.gassion88.security.jwt.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    User saveUser(User user);
    JwtAuthResponseDTO createAuthToken(JwtAuthRequestDTO jwtAuthRequestDTO);
}
