package com.nexus.cart.service;

import com.nexus.cart.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    public User findUserById(int userId);
    public User findUserProfileByJwt(String jwt);
}
