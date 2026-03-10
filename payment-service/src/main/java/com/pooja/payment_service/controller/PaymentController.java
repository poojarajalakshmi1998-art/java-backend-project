package com.pooja.payment_service.controller;

import com.pooja.payment_service.dto.PaymentRequest;
import com.pooja.payment_service.dto.PaymentResponse;
import com.pooja.payment_service.enums.PaymentMethod;
import com.pooja.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

private final PaymentService paymentService;
@PostMapping("/createpayments")
    public ResponseEntity<PaymentResponse> createPayment(@Valid  @RequestBody PaymentRequest paymentRequest){

        // paymentRequest.setPaymentmethod(PaymentMethod.UPI);
       return ResponseEntity.ok().body(paymentService.createpayment(paymentRequest));
 }
}
