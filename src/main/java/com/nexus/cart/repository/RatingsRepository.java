package com.nexus.cart.repository;

import com.nexus.cart.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingsRepository extends JpaRepository<Ratings, Integer> {
    @Query("SELECT r FROM Ratings r WHERE r.product.productId=:productId")
    public List<Ratings> getAllProductsRatings(@Param("productId") Integer productId);
}
