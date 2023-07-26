package com.gassion88.security.jwt.util.exception;

public class NameAlreadyTakenException extends RuntimeException{
    public NameAlreadyTakenException() {
        super("This name is already taken");
    }
}
