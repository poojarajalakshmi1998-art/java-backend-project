package com.pooja.payment_service.exception;

public class InvalidPaymentStatusException extends RuntimeException{

    public InvalidPaymentStatusException(String message) {
        super(message);
    }
}
