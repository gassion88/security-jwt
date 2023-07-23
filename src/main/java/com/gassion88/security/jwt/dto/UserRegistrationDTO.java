package com.gassion88.security.jwt.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
