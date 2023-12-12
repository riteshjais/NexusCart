package com.nexus.cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;
    @Column(name = "total_price")
    private int totalPrice;
    @Column(name = "total_discounted_price")
    private int totalDiscountedPrice;
    @Column(name = "discount")
    private int discount;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "total_item")
    private int totalItem;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();

    @OneToOne
    private Address shippingAddress;
    @Embedded
    private PaymentDetails paymentDetails=new PaymentDetails();

}
