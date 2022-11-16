package com.iimetra.order.ecommerce.service;

import com.iimetra.order.ecommerce.dto.AddressDto;
import com.iimetra.order.ecommerce.dto.OrderCreatedResponse;
import com.iimetra.order.ecommerce.dto.OrderDto;
import com.iimetra.order.ecommerce.dto.OrderItemDto;
import com.iimetra.order.ecommerce.enumeration.OrderStatus;
import com.iimetra.order.ecommerce.enumeration.PaymentStatus;
import com.iimetra.order.ecommerce.mapper.OrderDetailsMapper;
import com.iimetra.order.ecommerce.model.Address;
import com.iimetra.order.ecommerce.model.Order;
import com.iimetra.order.ecommerce.model.OrderItem;
import com.iimetra.order.ecommerce.model.OrderItemPk;
import com.iimetra.order.ecommerce.model.Payment;
import com.iimetra.order.ecommerce.repository.AddressRepository;
import com.iimetra.order.ecommerce.repository.OrderItemRepository;
import com.iimetra.order.ecommerce.repository.OrderRepository;
import com.iimetra.order.ecommerce.repository.PaymentRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;
    private final AddressRepository addressRepository;
    private final OrderDetailsMapper orderDetailsMapper = Mappers.getMapper(OrderDetailsMapper.class);

    @Override
    @Transactional
    public OrderCreatedResponse createOrder(OrderDto orderDto) {
        log.info("Creating Order for customer {}", orderDto.getCustomerId());

        Order order = generateOrder(orderDto);

        var savedOrder = orderRepository.save(order);

        String savedOrderId = savedOrder.getOrderId();
        var orderItemList = buildOrderItems(orderDto.getOrderItems(), savedOrderId);
        orderItemRepository.saveAll(orderItemList);

        return OrderCreatedResponse.builder()
            .orderId(savedOrderId)
            .orderStatus(savedOrder.getOrderStatus())
            .build();
    }

    @Override
    public OrderDto findOrderById(String id) {
        return orderRepository.findById(id)
            .map(orderDetailsMapper::toOrderDto)
            .orElseThrow();
    }

    @Override
    public void updateOrderStatus(String id, String status) {
        var order = orderRepository.findById(id).orElseThrow();
        order.setOrderStatus(status);//TODO use enums
        orderRepository.save(order);
    }

    private Order generateOrder(OrderDto orderDto) {
        var order = orderDetailsMapper.toOrderEntity(orderDto);
        order.setOrderId(UUID.randomUUID().toString());
        order.setCreatedAt(LocalDate.now());
        order.setOrderStatus(OrderStatus.PROCESSING.name());

        var payment = buildAndSavePayment(orderDto.getAmount(), orderDto.getPaymentMode());
        order.setPayment(payment);

        Address billingAddress = buildAndLoadAddress(orderDto.getBillingAddress());
        Address shippingAddress = buildAndLoadAddress(orderDto.getShippingAddress());
        order.setBillingAddress(billingAddress);
        order.setShippingAddress(shippingAddress);
        return order;
    }

    private List<OrderItem> buildOrderItems(List<OrderItemDto> orderItemsDtoList,
                                            String orderId) {
        var orderItemList = orderItemsDtoList.stream().map(orderItemDto ->
            new OrderItem(
                new OrderItemPk(orderItemDto.getProductId(), orderId),
                null,
                null,
                orderItemDto.getQuantity()
            )).collect(Collectors.toList());
        return (List<OrderItem>) orderItemRepository.saveAll(orderItemList);
    }

    private Payment buildAndSavePayment(double amount, String paymentMode) {
        var payment = new Payment(
            UUID.randomUUID().toString(),
            amount,
            paymentMode,
            UUID.randomUUID().toString(),
            PaymentStatus.PROCESSING.name(),
            LocalDate.now(),
            null
        );
        return paymentRepository.save(payment);
    }

    private Address buildAndLoadAddress(AddressDto addressDto) {
        var addressEntity = orderDetailsMapper.toAddressEntity(addressDto);
        addressEntity.setAddressId(UUID.randomUUID().toString());
        addressEntity.setCreatedAt(LocalDate.now());
        return addressRepository.save(addressEntity);
    }
}
