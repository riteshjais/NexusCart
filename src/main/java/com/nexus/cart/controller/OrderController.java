package com.nexus.cart.controller;

import com.nexus.cart.entity.Address;
import com.nexus.cart.entity.Order;
import com.nexus.cart.entity.User;
import com.nexus.cart.service.OrderService;
import com.nexus.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }
    @PostMapping("/create-order")
    public ResponseEntity<Object> createOrder(@RequestBody Address shippingAddress,
                                              @RequestHeader("Authorization") String jwt){
        User user=userService.findUserProfileByJwt(jwt);
        return new ResponseEntity<>(orderService.createOrder(user,shippingAddress), HttpStatus.CREATED);
    }
    @GetMapping("/user-order-history")
    public ResponseEntity<Object> usersOrderHistory(@RequestHeader("Authorization") String jwt){
        User user=userService.findUserProfileByJwt(jwt);
        List<Order> orders=orderService.userOrderHistory(user.getUserId());
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    @GetMapping("get-order-by-id/{orderId}")
    public ResponseEntity<Object> getOrderById(@PathVariable("orderId") Integer orderId){
        return new ResponseEntity<>(orderService.findOrderById(orderId),HttpStatus.OK);
    }

}
