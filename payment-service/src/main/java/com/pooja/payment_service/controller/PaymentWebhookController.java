package com.pooja.payment_service.controller;

import com.pooja.payment_service.dto.PaymentRequest;
import com.pooja.payment_service.dto.PaymentResponse;
import com.pooja.payment_service.dto.PaymentStatusUpdateRequest;
import com.pooja.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webhooks")
public class PaymentWebhookController {

    private final PaymentService paymentService;
    @PatchMapping("/payments/status")
    public ResponseEntity<PaymentResponse>updateStatus (@Valid @RequestBody PaymentStatusUpdateRequest paymentstatusrequest)
    {

       PaymentResponse updatepayment= paymentService.updatepayment( paymentstatusrequest);
       return ResponseEntity.ok().body(updatepayment);



    }
}
