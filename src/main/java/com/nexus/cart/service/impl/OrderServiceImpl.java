package com.nexus.cart.service.impl;

import com.nexus.cart.entity.Address;
import com.nexus.cart.entity.Order;
import com.nexus.cart.entity.User;
import com.nexus.cart.repository.CartRepository;
import com.nexus.cart.service.CartService;
import com.nexus.cart.service.OrderService;
import com.nexus.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private CartRepository cartRepository;
    private CartService cartService;
    private ProductService productService;

    @Autowired
    public OrderServiceImpl(CartRepository cartRepository, CartService cartService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartService = cartService;
        this.productService = productService;
    }



    @Override
    public Order createOrder(User user, Address shippingAddress) {
        return null;
    }

    @Override
    public Order findOrderById(int orderId) {
        return null;
    }

    @Override
    public List<Order> userOrderHistory(int userId) {
        return null;
    }

    @Override
    public Order placedOrder(int orderId) {
        return null;
    }

    @Override
    public Order shippedOrder(int orderId) {
        return null;
    }

    @Override
    public Order deliveredOrder(int orderId) {
        return null;
    }

    @Override
    public Order canceledOrder(int orderId) {
        return null;
    }
}
