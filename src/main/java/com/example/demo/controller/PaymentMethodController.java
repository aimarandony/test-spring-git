package com.example.demo.controller;

import com.example.demo.model.PaymentMethod;
import com.example.demo.service.PaymentMethodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodService.getSortedPaymentMethods();
    }
}