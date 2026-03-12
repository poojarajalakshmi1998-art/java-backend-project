package com.pooja.auth_service.Exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = { InvalidUserNameException.class })
    public ResponseEntity<?> handleRuntimeException(InvalidUserNameException ex)
    {

       String error = ex.getMessage();

       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex)
    {

        String error = ex.getMessage();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

    }

    @ExceptionHandler(value = { ExpiredJwtException.class })
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException ex)
    {

        String error = ex.getMessage();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);

    }
}
