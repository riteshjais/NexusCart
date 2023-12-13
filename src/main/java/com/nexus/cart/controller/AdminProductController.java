package com.nexus.cart.controller;

import com.nexus.cart.dto.ProductRequestDto;
import com.nexus.cart.entity.Product;
import com.nexus.cart.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/product")
public class AdminProductController {
    private ProductService productService;

    @Autowired
    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product")
    public ResponseEntity<Object> createProduct(@RequestBody ProductRequestDto payload){
        return new ResponseEntity<>(productService.createProduct(payload), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<Object> deleteProductById(@PathVariable("productId") Integer productId){
        return new ResponseEntity<>(productService.deleteProduct(productId), HttpStatus.OK);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<Object> getAllProducts(){
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }

    @PutMapping("/update-product/{productId}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product payload,
                                                @PathVariable("productId") Integer productId){
        return new ResponseEntity<>(productService.updateProduct(productId,payload), HttpStatus.OK);
    }

    @PostMapping("/create-multiple-product")
    public ResponseEntity<Object> createMultipleOrder(@RequestBody ProductRequestDto[] payload){
        Product product=new Product();
        for(ProductRequestDto productRequestDto:payload)
            product=productService.createProduct(productRequestDto);
        return new ResponseEntity<>("Products Created", HttpStatus.OK);
    }
}
