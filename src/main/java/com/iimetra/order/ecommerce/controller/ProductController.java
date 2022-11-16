package com.iimetra.order.ecommerce.controller;

import com.iimetra.order.ecommerce.dto.ProductDto;
import com.iimetra.order.ecommerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    @Operation(summary = "Create a product", description = "Create a product")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @GetMapping("/products/{productId}")
    @Operation(summary = "Get a product", description = "Get a product")
    public ProductDto getProduct(@PathVariable(name = "productId") String productId) {
        return productService.getProduct(productId);
    }
}
