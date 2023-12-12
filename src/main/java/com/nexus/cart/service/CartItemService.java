package com.nexus.cart.service;

import com.nexus.cart.entity.Cart;
import com.nexus.cart.entity.CartItem;
import com.nexus.cart.entity.Product;

public interface CartItemService {
    public CartItem creatCartItem(CartItem cartItem);
    public CartItem updateCartItem(Integer userId, Integer cartItemId, CartItem cartItem);
    public CartItem isCartItemExist(Cart cart, Product product,String size,Integer  userId);
    public void removeCartItem(Integer userId, Integer cartItemId);
    public CartItem findCartItemById(Integer cartItemId);
}
