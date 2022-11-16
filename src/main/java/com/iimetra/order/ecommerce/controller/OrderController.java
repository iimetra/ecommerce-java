package com.iimetra.order.ecommerce.controller;

import com.iimetra.order.ecommerce.dto.OrderCreatedResponse;
import com.iimetra.order.ecommerce.dto.OrderDto;
import com.iimetra.order.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    @Operation(summary = "Create an order", description = "Create an order")
    public OrderCreatedResponse createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/orders/{orderId}")
    public OrderDto findOrderBy(@PathVariable(name = "orderId") String orderId) {
        return orderService.findOrderById(orderId);
    }

    @PatchMapping("/orders/{orderId}")
    public void updateOrderStatus(@PathVariable("orderId") String orderId,
                                  @RequestParam(name = "orderStatus") String orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
    }

}
