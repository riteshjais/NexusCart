package com.nexus.cart.controller;

import com.nexus.cart.dto.PaymentLinkResponseDto;
import com.nexus.cart.entity.Order;
import com.nexus.cart.repository.OrderRepository;
import com.nexus.cart.service.OrderService;
import com.nexus.cart.service.UserService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("/api/v1/users/payment")
public class PaymentController {

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.merchant-id}")
    private String apiMerchantId;

    private OrderService orderService;
    private UserService userService;
    private OrderRepository orderRepository;

    @Autowired
    public PaymentController(OrderService orderService, UserService userService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderRepository = orderRepository;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<Object> createPaymentLink(@PathVariable("orderId") Integer orderId,
                                                    @RequestHeader("Authorization") String jwt) throws RazorpayException {

        Order order=orderService.findOrderById(orderId);
        try {
            RazorpayClient razorpayClient=new RazorpayClient(apiKey,apiMerchantId);
            JSONObject paymentLinkRequest=new JSONObject();
            paymentLinkRequest.put("amount",order.getTotalPrice()*100);
            paymentLinkRequest.put("currency","INR");

            JSONObject customer=new JSONObject();
            customer.put("Name",order.getUser().getName());
            customer.put("Email",order.getUser().getEmail());

            JSONObject notify=new JSONObject();
            notify.put("SMS",true);
            notify.put("Email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("customer",customer);
            //paymentLinkRequest.put("callback_url","http://localhost300/api/v1/payment/{orderId}");
            //paymentLinkRequest.put("callback_method","GET");
            PaymentLink paymentLink=razorpayClient.paymentLink.create(paymentLinkRequest);
            String paymentLinkId=paymentLink.get("id");
            String paymentLinkUrl=paymentLink.get("short_url");
            PaymentLinkResponseDto paymentLinkResponseDto=new PaymentLinkResponseDto();
            paymentLinkResponseDto.setPaymentLinkId(paymentLinkId);
            paymentLinkResponseDto.setPaymentLinkUrl(paymentLinkUrl);
            return new ResponseEntity<>(paymentLinkResponseDto, HttpStatus.CREATED);



        }catch (Exception e){
            throw new RazorpayException(e.getMessage());
        }

    }

    public ResponseEntity<Object> redirect(@RequestParam("payment_id")String paymentId,
                                           @RequestParam("orderId") Integer ordderId) throws RazorpayException {
        Order order=orderService.findOrderById(ordderId);
        RazorpayClient razorpayClient=new RazorpayClient(apiKey,apiMerchantId);
        Payment payment=razorpayClient.payments.fetch(paymentId);
        if (payment.get("status").equals("captured")){
            order.getPaymentDetails().setPaymentId(paymentId);
            order.getPaymentDetails().setStatus("COMPLETED");
            order.setOrderStatus("PLACED");
            return new ResponseEntity<>(orderRepository.save(order),HttpStatus.CREATED);
        }
        return new ResponseEntity<>("PAYMENT Failed",HttpStatus.BAD_REQUEST);
    }
}
