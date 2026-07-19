package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Payment;
import com.example.demo.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("/{orderId}")
    public Payment payment(@PathVariable Long orderId) {

        return service.makePayment(orderId);

    }

}