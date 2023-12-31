package com.gassion88.security.jwt.util.exception;

import com.gassion88.security.jwt.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler( BadCredentialsException.class )
    public ResponseEntity<ErrorResponseDTO> badCredentialsException () {
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Bad Credentials"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler( NameAlreadyTakenException.class )
    public ResponseEntity<ErrorResponseDTO> nameAlreadyTakenException (Exception e) {
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
