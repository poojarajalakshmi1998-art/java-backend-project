package com.pooja.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
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

