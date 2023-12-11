package com.nexus.cart.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentInformation {
    private String cardHolderName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvv;

}
