package com.nexus.cart.dto;

import com.nexus.cart.entity.Category;
import com.nexus.cart.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {
    private String title;
    private String description;
    private int quantity;
    private int price;
    private int discountedPrice;
    private int discountPercentage;
    private String brand;
    private String colour;
    private Set<Size> size=new HashSet<>();
    private String imageUrl;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
}
