package com.nexus.cart.controller;

import com.nexus.cart.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<Object> getUserProfile(@RequestHeader("Authorization") String jwt){
        return new ResponseEntity<>(userService.findUserProfileByJwt(jwt), HttpStatus.OK);

    }
}
