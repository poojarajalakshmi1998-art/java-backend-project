package com.pooja.auth_service.Exception;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> HandleMethodArgNotFoundException(MethodArgumentNotValidException ex) {

        Map<String,String> errors = new HashMap<>();
        List<FieldError> FieldErrors = ex.getBindingResult().getFieldErrors();

        for(FieldError fielderror : FieldErrors){

            errors.put(fielderror.getField(),fielderror.getDefaultMessage());
        }
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);



    }
}
