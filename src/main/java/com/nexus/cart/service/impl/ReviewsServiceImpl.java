package com.nexus.cart.service.impl;

import com.nexus.cart.dto.ReviewRequestDto;
import com.nexus.cart.entity.Product;
import com.nexus.cart.entity.Reviews;
import com.nexus.cart.entity.User;
import com.nexus.cart.repository.ProductRepository;
import com.nexus.cart.repository.ReviewsRepository;
import com.nexus.cart.service.ProductService;
import com.nexus.cart.service.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private ReviewsRepository reviewsRepository;
    private ProductService productService;
    private ProductRepository productRepository;

    @Autowired
    public ReviewsServiceImpl(ReviewsRepository reviewsRepository, ProductService productService, ProductRepository productRepository) {
        this.reviewsRepository = reviewsRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @Override
    public Reviews createReview(ReviewRequestDto requestDto, User user) {
        Product product=productService.getProductById(requestDto.getProductId());
        Reviews reviews=new Reviews();
        reviews.setUser(user);
        reviews.setProduct(product);
        reviews.setReview(requestDto.getReview());
        reviews.setCreatedAt(LocalDateTime.now());
        return reviewsRepository.save(reviews);
    }

    @Override
    public List<Reviews> getAllReview(Integer productId) {
        return reviewsRepository.getAllProductsReview(productId);
    }
}
