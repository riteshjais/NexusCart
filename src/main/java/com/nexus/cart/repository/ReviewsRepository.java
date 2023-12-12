package com.nexus.cart.repository;

import com.nexus.cart.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<Reviews,Integer> {
    @Query("SELECT r FROM Reviews r WHERE r.product.productId=:productId")
    public List<Reviews> getAllProductsReview(@Param("productId") Integer productId);
}
