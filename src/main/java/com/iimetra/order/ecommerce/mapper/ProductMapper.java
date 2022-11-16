package com.iimetra.order.ecommerce.mapper;

import com.iimetra.order.ecommerce.dto.ProductDto;
import com.iimetra.order.ecommerce.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

    Product toProductEntity(ProductDto productDto);

    ProductDto toProductDto(Product product);
}
