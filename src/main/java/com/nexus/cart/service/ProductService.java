package com.nexus.cart.service;

import com.nexus.cart.dto.ProductRequestDto;
import com.nexus.cart.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    public Product createProduct(ProductRequestDto requestDto);
    public String deleteProduct(int productId);
    public Product updateProduct(int productId, Product product);
    public Product getProductById(int productId);
    public List<Product> getProductByCategory(String category);
    public Page<Product> getAllProduct(String category, List<String> colours,
                                       List<String> sizes, int minPrice, int maxPrice,
                                       int minDiscount,String sort,String stock,
                                       int pageNumber,int pageSize);
    public List<Product> findAllProduct();


}
