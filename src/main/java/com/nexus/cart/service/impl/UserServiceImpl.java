package com.nexus.cart.service.impl;

import com.nexus.cart.config.JWTService;
import com.nexus.cart.entity.User;
import com.nexus.cart.exception.EntityNotFoundException;
import com.nexus.cart.repository.UserRepository;
import com.nexus.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private JWTService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public User findUserById(int userId) {
        Optional<User> user=userRepository.findById(userId);
        if(user.isEmpty())
            throw new EntityNotFoundException("User Not Fount with id: "+userId);
        return user.get();
    }

    @Override
    public User findUserProfileByJwt(String jwt) {
        System.out.println(jwt);
        String email=jwtService.extractUserName(jwt);
        System.out.println(email);
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isEmpty())
            throw new EntityNotFoundException("User Not Found ");
        return user.get();
    }
}
