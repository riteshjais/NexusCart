package com.nexus.cart.service.impl;

import com.nexus.cart.dto.RatingRequestDto;
import com.nexus.cart.entity.Product;
import com.nexus.cart.entity.Ratings;
import com.nexus.cart.entity.User;
import com.nexus.cart.repository.RatingsRepository;
import com.nexus.cart.service.ProductService;
import com.nexus.cart.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingsServiceImpl implements RatingsService {

    private RatingsRepository ratingsRepository;
    private ProductService productService;

    @Autowired
    public RatingsServiceImpl(RatingsRepository ratingsRepository, ProductService productService) {
        this.ratingsRepository = ratingsRepository;
        this.productService = productService;
    }

    @Override
    public Ratings createRating(RatingRequestDto ratingRequestDto, User user) {
        Product product=productService.getProductById(ratingRequestDto.getProductId());
        Ratings ratings=new Ratings();
        ratings.setProduct(product);
        ratings.setUser(user);
        ratings.setRating(ratingRequestDto.getRating());
        ratings.setCreatedAt(LocalDateTime.now());
        return ratingsRepository.save(ratings);
    }

    @Override
    public List<Ratings> getProductsRatings(Integer productId) {

        return ratingsRepository.getAllProductsRatings(productId);
    }
}
