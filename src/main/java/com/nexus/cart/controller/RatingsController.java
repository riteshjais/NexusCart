package com.nexus.cart.controller;

import com.nexus.cart.dto.RatingRequestDto;
import com.nexus.cart.entity.User;
import com.nexus.cart.service.RatingsService;
import com.nexus.cart.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ratings")
public class RatingsController {
    private UserService userService;
    private RatingsService ratingsService;

    public RatingsController(UserService userService, RatingsService ratingsService) {
        this.userService = userService;
        this.ratingsService = ratingsService;
    }

    @PostMapping("/create-rating")
    public ResponseEntity<Object> createRating(@RequestBody RatingRequestDto payload,
                                               @RequestHeader("Authorization") String jwt){
        User user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(ratingsService.createRating(payload,user), HttpStatus.CREATED);
    }
    @GetMapping("/get-product-rating/{productId}")
    public ResponseEntity<Object> getProductRating(@PathVariable("productId") Integer productId){
        return new ResponseEntity<>(ratingsService.getProductsRatings(productId), HttpStatus.OK);
    }

}
