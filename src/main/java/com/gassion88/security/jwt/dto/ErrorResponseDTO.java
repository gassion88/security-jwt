package com.gassion88.security.jwt.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponseDTO {
    private int status;
    private String message;
    private Date timestamp;

    public ErrorResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
