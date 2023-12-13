package com.nexus.cart.service;

import com.nexus.cart.entity.Address;
import com.nexus.cart.entity.Order;
import com.nexus.cart.entity.User;


import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address shippingAddress);
    public Order findOrderById(int orderId);
    public List<Order> userOrderHistory(int userId);
    public Order placedOrder(int orderId);
    public Order confirmedOrder(int orderId);
    public Order shippedOrder(int orderId);
    public Order deliveredOrder(int orderId);
    public Order canceledOrder(int orderId);
    public List<Order> getAllOrders();
    public void deletedOrderByOrderId(Integer orderId);
}
