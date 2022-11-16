package com.iimetra.order.ecommerce.service;

import com.iimetra.order.ecommerce.dto.OrderCreatedResponse;
import com.iimetra.order.ecommerce.dto.OrderDto;

public interface OrderService {
    OrderCreatedResponse createOrder(OrderDto orderDto);

    OrderDto findOrderById(String id);

    void updateOrderStatus(String id, String status);
}
