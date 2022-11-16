package com.iimetra.order.ecommerce.dto;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

// TODO cover dtos with swagger documentation
@Data
@Builder
public class ProductDto {
    @NotNull
    private final String productId;
    @NotNull
    private final String sku;
    @NotNull
    private final String title;
    @NotNull
    private final String description;
    private final double price;
}
