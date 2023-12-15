package com.nexus.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentLinkResponseDto {
    private String paymentLinkUrl;
    private String paymentLinkId;

}
