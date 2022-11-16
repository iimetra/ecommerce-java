package com.iimetra.order.ecommerce.service;

import com.iimetra.order.ecommerce.dto.ProductDto;

public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(String productId);
}
