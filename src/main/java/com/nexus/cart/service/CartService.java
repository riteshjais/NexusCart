package com.nexus.cart.service;

import com.nexus.cart.dto.AddCartItemRequestDto;
import com.nexus.cart.entity.Cart;
import com.nexus.cart.entity.User;

public interface CartService {
    public Cart creatCart(User user);
    public String addCartItem(Integer userId, AddCartItemRequestDto requestDTO);
    public Cart findUserCart(Integer userId);
}
