package com.iimetra.order.ecommerce.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrderCreatedResponse {
    @NotNull
    private final String orderId;
    @NotNull
    private final String orderStatus;
}
