package com.nexus.cart.service.impl;

import com.nexus.cart.entity.Cart;
import com.nexus.cart.entity.CartItem;
import com.nexus.cart.entity.Product;
import com.nexus.cart.entity.User;
import com.nexus.cart.exception.EntityNotFoundException;
import com.nexus.cart.repository.CartItemRepository;
import com.nexus.cart.repository.CartRepository;
import com.nexus.cart.service.CartItemService;
import com.nexus.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository, UserService userService, CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItem creatCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Integer userId, Integer cartItemId, CartItem cartItem) {
        CartItem item=findCartItemById(cartItemId);
        User user=userService.findUserById(item.getUserId());
        if(user.getUserId()==userId){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getQuantity()*item.getProduct().getDiscountedPrice());
        }
        return cartItemRepository.save(item);

    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Integer userId) {
        CartItem cartItem=cartItemRepository.isCartItemExists(cart, product,size,userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Integer userId, Integer cartItemId) {
        CartItem cartItem=findCartItemById(cartItemId);
        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userId);
        if(user.getUserId()==reqUser.getUserId())
            cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public CartItem findCartItemById(Integer cartItemId) {
        Optional<CartItem> cartItem=cartItemRepository.findById(cartItemId);
        if(cartItem.isEmpty())
            throw new EntityNotFoundException("CartItem Not Found");
        return cartItem.get();
    }
}
