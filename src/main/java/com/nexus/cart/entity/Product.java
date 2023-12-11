package com.nexus.cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "discounted_price")
    private int discountedPrice;
    @Column(name = "discount_percentage")
    private int discountPercentage;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "brand")
    private String brand;
    @Column(name = "colour")
    private String colour;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "num_ratings")
    private int numRatings;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Reviews> reviews=new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Ratings> ratings=new ArrayList<>();

    @Embedded
    @ElementCollection
    @CollectionTable(name = "sizes")
    private Set<Size> sizes=new HashSet<>();
}
