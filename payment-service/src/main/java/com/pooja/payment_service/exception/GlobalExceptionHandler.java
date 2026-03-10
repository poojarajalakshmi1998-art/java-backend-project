package com.pooja.payment_service.exception;

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

    @ExceptionHandler(value=RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException e){

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){


        Map<String,String> errors = new HashMap<>();

       List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

       for(FieldError fieldError : fieldErrors){
           errors.put(fieldError.getField(),fieldError.getDefaultMessage());
       }
return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(value=InvalidPaymentStatusException.class)
    public ResponseEntity<Map<String,String>> handleInvalidPaymentStatusException(InvalidPaymentStatusException ex )
    {
       Map<String,String> error=new HashMap<>();
        error.put("message",ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }
}
