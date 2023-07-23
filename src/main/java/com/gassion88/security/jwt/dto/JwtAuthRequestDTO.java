package com.gassion88.security.jwt.dto;

import lombok.Data;

@Data
public class JwtAuthRequestDTO {
    private String username;
    private String password;
}
