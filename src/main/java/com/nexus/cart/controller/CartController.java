package com.nexus.cart.controller;

import com.nexus.cart.dto.AddCartItemRequestDto;
import com.nexus.cart.entity.User;
import com.nexus.cart.service.CartService;
import com.nexus.cart.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private CartService cartService;
    private UserService userService;

    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/find-user-cart")
    public ResponseEntity<Object> findUserCart(@RequestHeader("Authorization") String jwt){
        User user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(cartService.findUserCart(user.getUserId()), HttpStatus.OK);
    }
    @PutMapping("/add-item-to-cart")
    public ResponseEntity<Object> addItemToCart(@RequestBody AddCartItemRequestDto payload,
                                                @RequestHeader("Authorization") String jwt){
        User user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(cartService.addCartItem(user.getUserId(), payload), HttpStatus.OK);
    }


}
