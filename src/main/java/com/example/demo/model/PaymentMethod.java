package com.example.demo.model;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class PaymentMethod {
    private String name;
    private String number;
}