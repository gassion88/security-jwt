package com.gassion88.security.jwt.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NameAlreadyTakenException extends RuntimeException{
    public NameAlreadyTakenException() {
        super("This name is already taken");
    }
}
