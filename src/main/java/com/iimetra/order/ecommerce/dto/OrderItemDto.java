package com.iimetra.order.ecommerce.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderItemDto {

    @NotNull
    private final String productId;
    @NotNull
    private final String quantity;
}
