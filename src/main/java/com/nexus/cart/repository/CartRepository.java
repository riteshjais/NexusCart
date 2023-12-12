package com.nexus.cart.repository;

import com.nexus.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart,Integer> {

    @Query("SELECT c FROM Cart c WHERE c.user.userId=:userId")
    public Cart findCartByUserId(@Param("userId") int userId);
}
