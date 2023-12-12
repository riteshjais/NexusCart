package com.nexus.cart.service;

import com.nexus.cart.dto.ReviewRequestDto;
import com.nexus.cart.entity.Reviews;
import com.nexus.cart.entity.User;

import java.util.List;

public interface ReviewsService {
    public Reviews createReview(ReviewRequestDto requestDto, User user);
    public List<Reviews> getAllReview(Integer productId);

}
