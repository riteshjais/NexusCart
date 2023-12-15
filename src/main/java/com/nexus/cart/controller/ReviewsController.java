package com.nexus.cart.controller;

import com.nexus.cart.dto.ReviewRequestDto;
import com.nexus.cart.entity.User;
import com.nexus.cart.service.ReviewsService;
import com.nexus.cart.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/reviews")
public class ReviewsController {
    private ReviewsService reviewsService;
    private UserService userService;

    public ReviewsController(ReviewsService reviewsService, UserService userService) {
        this.reviewsService = reviewsService;
        this.userService = userService;
    }

    @PostMapping("/create-review")
    public ResponseEntity<Object> createReview(@RequestBody ReviewRequestDto payload,
                                               @RequestHeader("Authorization") String jwt){
        User user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(reviewsService.createReview(payload,user) ,HttpStatus.CREATED);
    }

    @GetMapping("/get-product-review/{productId}")
    public ResponseEntity<Object> getProductReview(@PathVariable("productId") Integer productId){
        return new ResponseEntity<>(reviewsService.getAllReview(productId), HttpStatus.OK);
    }

}
