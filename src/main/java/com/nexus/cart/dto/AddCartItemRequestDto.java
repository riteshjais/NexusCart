package com.nexus.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddCartItemRequestDto {
    private int productId;
    private String size;
    private int quantity;
    private int price;
}
