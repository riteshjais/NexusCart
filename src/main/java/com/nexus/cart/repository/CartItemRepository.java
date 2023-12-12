package com.nexus.cart.repository;

import com.nexus.cart.entity.Cart;
import com.nexus.cart.entity.CartItem;
import com.nexus.cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query("SELECT ci FROM CartItem ci "+
        "WHERE ci.cart=:cart AND ci.product=:product "+
        "AND ci.size=:size AND ci.userId=:userId")
    public CartItem isCartItemExists(@Param("cart") Cart cart,
                                     @Param("product")Product product,
                                     @Param("size") String size,
                                     @Param("userId") int userId);
}
