package com.nexus.cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private int orderItemId;
    @Column(name = "size")
    private String size;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private int price;
    @Column(name = "discounted_price")
    private int discountedPrice;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;


    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

}
