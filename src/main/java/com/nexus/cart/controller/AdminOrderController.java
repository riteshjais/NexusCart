package com.nexus.cart.controller;

import com.nexus.cart.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/order")
public class AdminOrderController {
    private OrderService orderService;

    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<Object> getAllOrders(){
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @PutMapping("/confirm-order/{orderId}")
    public ResponseEntity<Object> confirmOrder(@PathVariable("orderId") Integer orderId){
        return new ResponseEntity<>(orderService.confirmedOrder(orderId),HttpStatus.OK);
    }

    @PutMapping("/shipped-order/{orderId}")
    public ResponseEntity<Object> shippedOrder(@PathVariable("orderId") Integer orderId){
        return new ResponseEntity<>(orderService.shippedOrder(orderId),HttpStatus.OK);
    }

    @PutMapping("/delivered-order/{orderId}")
    public ResponseEntity<Object> deliveredOrder(@PathVariable("orderId") Integer orderId){
        return new ResponseEntity<>(orderService.deliveredOrder(orderId),HttpStatus.OK);
    }

    @PutMapping("/cancel-order/{orderId}")
    public ResponseEntity<Object> cancelOrder(@PathVariable("orderId") Integer orderId){
        return new ResponseEntity<>(orderService.canceledOrder(orderId),HttpStatus.OK);
    }

    @DeleteMapping("/delete-order/{orderId}")
    public ResponseEntity<Object> deleteOrderById(@PathVariable("orderId") Integer orderId){
        orderService.deletedOrderByOrderId(orderId);
        return new ResponseEntity<>("Order Deleted: ",HttpStatus.OK);
    }
}
