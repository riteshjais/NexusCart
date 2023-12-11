package com.nexus.cart.service;

import com.nexus.cart.entity.User;

public interface UserService {
    public User findUserById(int userId);
    public User finduserProfileByJwt(String jwt);
}
