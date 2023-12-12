package com.nexus.cart.service;

import com.nexus.cart.dto.RatingRequestDto;
import com.nexus.cart.entity.Ratings;
import com.nexus.cart.entity.User;

import java.util.List;

public interface RatingsService {
    public Ratings createRating(RatingRequestDto ratingRequestDto, User user);
    public List<Ratings> getProductsRatings(Integer productId);
}
