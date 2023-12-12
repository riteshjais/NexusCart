package com.nexus.cart.service.impl;

import com.nexus.cart.dto.AddCartItemRequestDto;
import com.nexus.cart.entity.Cart;
import com.nexus.cart.entity.CartItem;
import com.nexus.cart.entity.Product;
import com.nexus.cart.entity.User;
import com.nexus.cart.repository.CartRepository;
import com.nexus.cart.service.CartItemService;
import com.nexus.cart.service.CartService;
import com.nexus.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart creatCart(User user) {
        Cart cart=new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Integer userId, AddCartItemRequestDto requestDTO) {
        Cart cart=cartRepository.findCartByUserId(userId);
        Product product=productService.getProductById(requestDTO.getProductId());
        CartItem isPresent=cartItemService.isCartItemExist(cart,product,requestDTO.getSize(),userId);
        if(isPresent==null){
            CartItem cartItem=new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(requestDTO.getQuantity());
            cartItem.setUserId(userId);
            int price=requestDTO.getQuantity()*product.getDiscountedPrice();
            cartItem.setPrice(price);
            cartItem.setSize(requestDTO.getSize());
            CartItem createdCartItem=cartItemService.creatCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        return "Item Added To Cart";
    }

    @Override
    public Cart findUserCart(Integer userId) {
        Cart cart=cartRepository.findCartByUserId(userId);
        int totalPrice=0;
        int totalDiscountedPrice=0;
        int totalItem=0;
        for(CartItem cartItem:cart.getCartItems()){
            totalPrice+=cartItem.getPrice();
            totalDiscountedPrice+=cartItem.getDiscountedPrice();
            totalItem+=cartItem.getQuantity();
        }
        cart.setTotalItem(totalItem);
        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice-totalDiscountedPrice);
        return cartRepository.save(cart);
    }
}
