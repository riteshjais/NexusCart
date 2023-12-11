package com.nexus.cart.service.impl;

import com.nexus.cart.entity.User;
import com.nexus.cart.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(int userId) {
        return null;
    }

    @Override
    public User findUserProfileByJwt(String jwt) {
        return null;
    }
}
