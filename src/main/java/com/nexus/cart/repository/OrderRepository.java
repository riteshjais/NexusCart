package com.nexus.cart.repository;

import com.nexus.cart.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("SELECT o FROM Order o WHERE o.user.userId=:userId "+
        "AND (o.orderStatus = 'PLACED' OR o.orderStatus= 'CONFIRMED' "+
            "OR o.orderStatus='DELIVERED' OR o.orderStatus='SHIPPED')")
    public List<Order> getUserOrders(@Param("userId") Integer userId);
}
