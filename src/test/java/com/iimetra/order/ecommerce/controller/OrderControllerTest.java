package com.iimetra.order.ecommerce.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iimetra.order.ecommerce.dto.OrderCreatedResponse;
import com.iimetra.order.ecommerce.dto.OrderDto;
import com.iimetra.order.ecommerce.enumeration.OrderStatus;
import com.iimetra.order.ecommerce.service.OrderService;
import com.iimetra.order.ecommerce.util.OrderUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    private static final OrderDto orderDtoRequest = OrderUtils.createTestOrder();
    private static final OrderCreatedResponse mockOrderCreateResponse = new OrderCreatedResponse("2e99fe21-2243-4004-9640-e992bbcc5040", "PROCESSING");

    @Mock
    private OrderService orderService;
    @InjectMocks
    private OrderController orderController;


    @Test
    void testCreateOrder() {
        when(orderService.createOrder(orderDtoRequest))
            .thenReturn(mockOrderCreateResponse);

        var actualResponse = orderController.createOrder(orderDtoRequest);

        assertThat(actualResponse).isEqualTo(mockOrderCreateResponse);
    }

    @Test
    void testGetOrder() {
        String orderId = "2e99fe21-2243-4004-9640-e992bbcc5040";
        when(orderService.findOrderById(orderId))
            .thenReturn(orderDtoRequest);

        var actualResponse = orderController.findOrderBy(orderId);

        assertThat(actualResponse).isEqualTo(orderDtoRequest);
    }

    @Test
    void testPatchOrder() {
        String orderId = "2e99fe21-2243-4004-9640-e992bbcc5040";

        orderController.updateOrderStatus(orderId, OrderStatus.CANCELLED.name());

        verify(orderService).updateOrderStatus(orderId, OrderStatus.CANCELLED.name());
    }
}
