package com.iimetra.order.ecommerce.service;

import com.iimetra.order.ecommerce.dto.ProductDto;
import com.iimetra.order.ecommerce.mapper.ProductMapper;
import com.iimetra.order.ecommerce.model.Product;
import com.iimetra.order.ecommerce.repository.ProductRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.info("Creating Product with productId {}", productDto.getProductId());
        Product entity = productMapper.toProductEntity(productDto);
        entity.setCreatedAt(LocalDate.now());
        Product savedProduct = productRepository.save(entity);
        return productMapper.toProductDto(savedProduct);
    }

    @Override
    public ProductDto getProduct(String productId) {
        log.info("Get Product by productId {}", productId);
        return productRepository.findById(productId)
            .map(productMapper::toProductDto)
            .orElseThrow();
    }
}
