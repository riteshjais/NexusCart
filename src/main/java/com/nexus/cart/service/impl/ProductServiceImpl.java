package com.nexus.cart.service.impl;

import com.nexus.cart.dto.ProductRequestDto;
import com.nexus.cart.entity.Category;
import com.nexus.cart.entity.Product;
import com.nexus.cart.exception.EntityNotFoundException;
import com.nexus.cart.repository.CategoryRepository;
import com.nexus.cart.repository.ProductRepository;
import com.nexus.cart.service.ProductService;
import com.nexus.cart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private UserService userService;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserService userService,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product createProduct(ProductRequestDto requestDto) {
        Category topLevelCategory=categoryRepository.findByName(requestDto.getTopLevelCategory());
        if(topLevelCategory==null){
            Category category=new Category();
            category.setName(requestDto.getTopLevelCategory());
            category.setLevel(1);
            categoryRepository.save(category);
        }

        Category secondLevelCategory=categoryRepository.findByNameAndParent(
                requestDto.getSecondLevelCategory(), topLevelCategory.getName()
        );
        if(secondLevelCategory==null){
            Category category= Category.builder()
                    .name(requestDto.getSecondLevelCategory())
                    .parentCategory(topLevelCategory)
                    .level(2)
                    .build();
            categoryRepository.save(category);
        }

        Category thirdCategory=categoryRepository.findByNameAndParent(requestDto.getThirdLevelCategory(),
                secondLevelCategory.getName());

        if(thirdCategory==null){
            Category category= Category.builder()
                    .name(requestDto.getThirdLevelCategory())
                    .parentCategory(secondLevelCategory)
                    .level(3)
                    .build();
            categoryRepository.save(category);
        }


        Product product=Product.builder()
                .title(requestDto.getTitle())
                .brand(requestDto.getBrand())
                .createdAt(LocalDateTime.now())
                .price(requestDto.getPrice())
                .category(thirdCategory)
                .colour(requestDto.getColour())
                .description(requestDto.getDescription())
                .discountedPrice(requestDto.getDiscountedPrice())
                .discountPercentage(requestDto.getDiscountPercentage())
                .imageUrl(requestDto.getImageUrl())
                .quantity(requestDto.getQuantity())
                .sizes(requestDto.getSize())
                .build();

        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(int productId) {
        Product product=getProductById(productId);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product with id: "+productId+" Got Deleted";

    }

    @Override
    public Product updateProduct(int productId, Product product) {
        Product existingProduct=getProductById(productId);
        existingProduct.setBrand(product.getBrand());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setColour(product.getColour());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCreatedAt(product.getCreatedAt());
        existingProduct.setDiscountedPrice(product.getDiscountedPrice());
        existingProduct.setDiscountPercentage(product.getDiscountPercentage());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setNumRatings(product.getNumRatings());
        return productRepository.save(existingProduct);
    }

    @Override
    public Product getProductById(int productId) {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isEmpty()){
            throw new EntityNotFoundException("Product with id: "+productId+" Not Found");
        }
        return product.get();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return null;
    }

    @Override
    public Page<Product> getAllProduct(String category, List<String> colours,
                                       List<String> sizes, int minPrice, int maxPrice,
                                       int minDiscount, String sort, String stock,
                                       int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProduct(category, minPrice, maxPrice, minDiscount, sort);
        if (!colours.isEmpty()) {
            products = products.stream().filter(p ->
                    colours.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColour()))
            ).collect(Collectors.toList());
        }
        if (stock != null) {
            if (stock.equals("in_stock")) {
                products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
            } else if (stock.equals("out_of_stock")) {
                products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
            }
        }
        int startIndex=(int) pageable.getOffset();
        int endIndex=Math.min(startIndex+pageable.getPageSize(),products.size());
        List<Product> pageContent=products.subList(startIndex,endIndex);
        Page<Product> filteredProduct=new PageImpl<>(pageContent,pageable,products.size());
        return filteredProduct;
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }
}
