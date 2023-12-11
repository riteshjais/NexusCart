package com.nexus.cart.repository;

import com.nexus.cart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p "+
        "WHERE (p.category= :category OR :category='') "+
        "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice AND :maxPrice)) "+
        "AND (:minDiscount IS NULL OR p.discountPercentage>= :minDiscount) "+
        "ORDER BY "+
        "CASE WHEN :sort='price_low' THEN p.discountedPrice END ASC, "+
        "CASE WHEN :sort='price_high' THEN p.discountedPrice END DESC")
    public List<Product> filterProduct(@Param("category") String category,
                                       @Param("minPrice") int minPrice,
                                       @Param("maxPrice") int maxPrice,
                                       @Param("minDiscount") int minDiscount,
                                       @Param("sort") String sort);

}
