package com.nexus.cart.controller;

import com.nexus.cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get-products-by-category")
    public ResponseEntity<Object> findProductByCategory(@RequestParam String category,@RequestParam List<String> colours,
                                                        @RequestParam List<String> sizes,@RequestParam int minPrice, int maxPrice,
                                                        @RequestParam int minDiscount,@RequestParam String sort, String stock,
                                                        @RequestParam int pageNumber,@RequestParam int pageSize){
        return new ResponseEntity<>(productService.getAllProduct(category,colours,
                                                                        sizes,minPrice,maxPrice,minDiscount,
                                                                        sort,stock,pageNumber,pageSize), HttpStatus.OK);
    }

    @GetMapping("/get-product-by-id/{productId}")
    public ResponseEntity<Object> getProductById(@PathVariable("productId") Integer productId){
        return new ResponseEntity<>(productService.getProductById(productId),HttpStatus.OK);
    }
}
